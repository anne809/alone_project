package com.toy.mytoy.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.toy.mytoy.domain.Board;
import com.toy.mytoy.service.BoardService;
//import com.toy.mytoy.service.CommentService;
import com.toy.mytoy.domain.Member;

import com.toy.mytoy.service.LoginMemberService;

/*


*/
//�츮 ������ ������� BoardListAction ���ݾƿ�? �װ� �����Ͻø� �ǿ�. �⺻ ������ ���ϴ°� ���� ����ؿ�...

@Controller
public class BoardController {

	@Autowired //���� ���� BoardService ����
	private BoardService boardService;
	
	//÷�����Ͽ�
	@Value("{savefoldername}")
	private String saveFolder;

	@Autowired
	private LoginMemberService loginmemberservice;
	
	//�� ��� ����
	@RequestMapping(value="/BoardList.bo")
	public ModelAndView boardList(@RequestParam(value="page",defaultValue="1",
			required=false)int page, ModelAndView mv, Model m, HttpSession session) {
		
		int limit = 10; //�� ȭ�鿡 ����� ���ڵ� ����
		
		int listcount = boardService.getListCount(); //��ü �� ���� �޾ƿ�
		
		//�� ������ ��
		int maxpage = (listcount + limit -1 )/ limit; 
		//��ü �� ���� + ��ȭ�� ����� �۰��� -1 / ��ȭ�� ����� �۰���
		//100��+10��-1�� / 10�� = 109 / 10 = 10.9��
		
		//���� �������� ������ ���� ������ ��(1, 11, 21...)
		//��û�Ķ���� �⺻�� 1�� ���� > startpage�� 1
		int startpage = ((page -1) / 10 ) * 10 + 1;
		
		//���� �������� ������ ������ ������ ��(10, 20, 30...)
		int endpage = startpage + 10 - 1; //��û �Ķ���� 1�� ���� > endpage�� 10
		
		//���� �������� �� ���� ������ ������ ������ �� ũ�ٸ�, 
		if(endpage > maxpage)
			endpage = maxpage; //������������������ ��� ����
		
		//�۸�� ����, page�� 1, limit ���ڵ� ���� ���� �Ѱܼ� ���� ��ȸ
		List<Board> boardlist = boardService.getBoardList(page, limit);
		
		//������ ���ҷ� model �߰�
		m.addAttribute("page", "board/qna_board_list.jsp");
		
		//mv.setViewName("board/qna_board_list");
		mv.setViewName("home");
		mv.addObject("page1", page); //jsp > page -> page1���� ����
		mv.addObject("maxpage",maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);
		
		//�α��� �Ǿ� �ִ� ���
		String id = (String)session.getAttribute("M_CODE");
		if(id != null) {
			Member memberinfo = loginmemberservice.getInfo(id);
			mv.addObject("memberinfo",memberinfo);
		}
		
		return mv;
	}
	
	//�۾���
	@GetMapping(value="/BoardWrite.no")
	public String board_write(Model m,ModelAndView mv, HttpSession session) {
		m.addAttribute("page", "board/qna_board_form.jsp");
		String id = (String)session.getAttribute("M_CODE");
		if(id != null) {
			Member memberinfo = loginmemberservice.getInfo(id);
			mv.addObject("memberinfo",memberinfo);
		}
		return "home";
	}
	
	//�� �����ϱ�
	@PostMapping(value = "Board_write_ok.no")
	public ModelAndView board_write_ok(Board board,HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:BoardList.bo");
		MultipartFile uploadfile = board.getUploadfile();
		
		//1.÷������ �����ҽ� ����
		if(!uploadfile.isEmpty()) {
			
			String fileName = uploadfile.getOriginalFilename();//���� ���ϸ�
			board.setBOARD_FILE(fileName); //���� ���ϸ� ����
			
			 // ���ο� ���� �̸� : ���� - �� - �� - ��
	         Calendar c = Calendar.getInstance();
	         int year = c.get(Calendar.YEAR); // ���� �⵵ ���մϴ�.
	         int month = c.get(Calendar.MONTH) + 1; // ���� �� ���մϴ�.
	         int date = c.get(Calendar.DATE); // ���� �� ���մϴ�.
	         String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";
	         String homedir = saveFolder + year + "-" + month + "-" + date;
	         System.out.println(homedir);
	         File path1 = new File(homedir);
	         if (!(path1.exists())) {
	            path1.mkdirs(); // ���ο� ������ ����
	         }

	         // ������ ���մϴ�.
	         Random r = new Random();
	         int random = r.nextInt(100000000);

	         /* Ȯ���� ���ϱ� ���� */
	         int index = fileName.lastIndexOf(".");
	         // ���ڿ����� Ư�� ���ڿ��� ��ġ �� (index)�� ��ȯ�Ѵ�.
	         // indexOf�� ó�� �߰ߵǴ� ���ڿ��� ���� index�� ��ȯ�ϴ� �ݸ�,
	         // lastIndextOf�� ������������ �߰ߵǴ� ���ڿ��� index�� ��ȯ�մϴ�.
	         // (���ϸ� ���� ������ ���� ��� �� �������� �߰ߵǴ� ���ڿ��� ��ġ�� �����մϴ�.)
	         System.out.println("index = " + index);

	         String fileExtension = fileName.substring(index + 1);
	         System.out.println("fileExtension = " + fileExtension);
	         /* Ȯ���� ���ϱ� �� */

	         // ���ο� ���ϸ�
	         String refileName = "bbs" + year + month + date + random + "." + fileExtension;
	         System.out.println("refileName = " + refileName);

	         // ����Ŭ ��� ����� ���� ��
	         String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
	         System.out.println("fileDBName = " + fileDBName);

	         // transferTo(File path) : ���ε��� ������ �Ű������� ��ο� �����մϴ�.
	         uploadfile.transferTo(new File(saveFolder + fileDBName));
	         
	      // �ٲ� ���ϸ� ����
	         board.setBOARD_FILE(fileDBName);
	         
			
		}
		//2. �� ���� ���� //�� �ۼ�, ��� ���� > imple ����
		boardService.insertBoard(board);
		
		return mv;
	}
	
		//�� �󼼺���
		@GetMapping("/BoardDetailAction.no")
		public ModelAndView Detail(int num, ModelAndView mv, HttpServletRequest request, Model m) {
			// �� �ҷ����� ���� > ��ȸ�� ������Ű�� �۳��� �ҷ����� ����
			Board board = boardService.getDetail(num);
			if (board == null) {
				System.out.println("�󼼺��� ����");
				m.addAttribute("page", "error/error_page.jsp");
				mv.setViewName("home");
				//��ü ��� �������� �Լ�  http://localhost:8080/mytoy/boardlist.jsp
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "�󼼺��� �����Դϴ�.");
			} else {
				System.out.println("�� �󼼺��� ����");
				m.addAttribute("page", "board/qna_board_view.jsp");
				mv.setViewName("home");
				mv.addObject("boarddata", board);
			}
			return mv;
		}
		
		
		//�� �����ϱ� 1 (�� �󼼺��� ������ ���� ����, ����� ��й�ȣ üũ)
		@GetMapping("/BoardModifyView.no")
		public ModelAndView BoardModifyView
		(int num, ModelAndView mv,HttpServletRequest request,Model m) {
			Board boarddata = boardService.getDetail(num);
			if(boarddata == null) {
				System.out.println("(�����ϱ�) �󼼺��� ����");
				m.addAttribute("page", "error/error_page");
				mv.setViewName("home");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "(�����ϱ�) �󼼺��� �����Դϴ�.");
				return mv;
			}
			System.out.println("(�����ϱ�) �󼼺��� ����");
			//�̵��� ������ �Ӽ��� ����ְ�
			m.addAttribute("page", "board/qna_board_modify.jsp");
			mv.setViewName("home");
			//�����Ͱ��� �����ش�.
			mv.addObject("boarddata", boarddata);
			
			return mv;
		}
		
		//�� �����ϱ� 2(��й�ȣ üũ ���� : �۹�ȣ�� ��й�ȣ�θ� üũ)
		@PostMapping("BoardModifyAction.no")
		public ModelAndView BoardModifyAction
		(String before_file, Board board, String check, ModelAndView mv, HttpServletRequest request,
		HttpServletResponse response, Model m) throws Exception {
			//�۹�ȣ�� ��й�ȣ ��Ī�Ǵ��� ���� ��ȸ
			int sample = board.getBOARD_NUM();
			String sample2 = board.getBOARD_PASS();
			System.out.println("�۹�ȣ ��� " + sample + "�� ��й�ȣ ��� " + sample2);
			
			boolean usercheck = boardService.isBoardWriter(board.getBOARD_NUM(), board.getBOARD_PASS());

			//��й�ȣ�� �ٸ� ���
			if(usercheck == false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			
			//��й�ȣ�� �´� ��� 
			//÷������ ���� ����
			MultipartFile uploadfile = board.getUploadfile();
			String saveFolder = request.getSession().getServletContext().getRealPath("resources")+ "/upload/";
			
			//modifyform.js���� ���ϸ� üũ���� �������� �Ѱ���
			System.out.println("check= "+check);
			
			//1. ���� ÷������ ������
			if(check != null && !check.contentEquals("")) {
				board.setBOARD_ORIGINAL(check);
			}else { //2. ÷������ �����
				if(uploadfile != null && !uploadfile.isEmpty()) {
					System.out.println("÷�������� ����Ǿ����ϴ�.");
					
					//���� ���ϸ�
					String fileName = uploadfile.getOriginalFilename();
					//���ϸ� ����
					board.setBOARD_ORIGINAL(fileName);
					// DB�� �� ���ϸ� �����ؼ� �޾ƿ��� ����
					String fileDBName = fileDBName(fileName,saveFolder);
					
					//transferTo(fILE path) : ���ε��� ������ �Ű������� ���� ��ο� ����
					uploadfile.transferTo(new File(saveFolder+fileDBName));
				
					//����� ���� �� > �����ο� ���
					board.setBOARD_FILE(fileDBName);
				}else { //3. ÷�������� �������� ���� ���  uploadfile.isEmpty()
					System.out.println("������ ÷�������� �����ϴ�.");
					
					//�� ���ϸ����� ������ �ʱ�ȭ("")�ؼ� ���
					board.setBOARD_FILE("");
					board.setBOARD_ORIGINAL("");
				}
			}

			//���� ���� ������ board�� DAO�� �޼��带 �ҷ� ������ �ݿ��ؼ� ��ȯ�� �޾ƿ�
			int result = boardService.boardModify(board);
			
			//1. �ݿ� ���н� ������������ �̵��ϵ��� �� ��´�
			if(result == 0) {
				System.out.println("�� ���� ����");
				mv.setViewName("error/error_page");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "�� ���� ����");
			}else {//2. �� ���� ����
				System.out.println("�� ���� �Ϸ�");
				
				// �������� ������ �ְ� ���ο� ������ ������ ���� ������ ����� ���̺� �߰��մϴ�.
				//if(!before_file.equals("") && !before_file.equals(board.getEV_FILE())) {
				//	eventboardService.insert_deleteFile(before_file);
				//}
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('���� �Ǿ����ϴ�.');");
				out.println("location.href="+"'BoardDetailAction.no?num="+board.getBOARD_NUM()+"';");
				out.println("</script>");
				out.close();
			}
			return mv;
		}
		
		// DB�� �� ���� ����
		private String fileDBName(String fileName, String saveFolder) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + year + "-" + month + "-" + date;
			System.out.println("homedir= "+homedir);
			File path1 = new File(homedir);
			System.out.println("path1 = "+ path1);
			if (!(path1.exists())) {
				path1.mkdirs(); // ���ο� ������ ����, mkdir()�� ������� �ϴ� ���丮�� ���� ���丮�� �������� ���� ���, ���� �Ұ�
				System.out.println("���ο� ���� ���� ����");
			}

			Random r = new Random();
			int random = r.nextInt(100000000);

			// Ȯ���� ���ϱ� ����
			int index = fileName.lastIndexOf(".");
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);

			// ���ο� ���ϸ�
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			System.out.println("refileName = " + refileName);

			// ����Ŭ ��� ����� ���� ��
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);
			return fileDBName;
		}
		
		//�� ����
		@RequestMapping(value = "/BoardDeleteAction.no")
		public ModelAndView BoardDeleteAction(String BOARD_PASS, int num,
				ModelAndView mv, 
				HttpServletResponse response,
				HttpServletRequest request
				) throws Exception{
			System.out.println("�ۻ��� num = "+ num);
			System.out.println("�ۻ��� BOARD_PASS= "+ BOARD_PASS);
			// �� ���� ����� ��û�� ����ڰ� ���� �ۼ��� ��������� �Ǵ��ϱ� ����
			// �Է��� ��й�ȣ�� ����� ��й�ȣ�� ���Ͽ� ��ġ�ϸ� �����մϴ�.
			boolean usercheck = boardService.isBoardWriter(num, BOARD_PASS);
			
			// ��й�ȣ ��ġ���� ���� ���
			if (usercheck == false) {
				System.out.println("usercheck false ���� ");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� �ٸ��ϴ�');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			// ��й�ȣ ��ġ�ϴ� ��� ���� ó�� �մϴ�.
			int result = boardService.boardDelete(num);
			
			// ����ó�� ������ ���
				if(result == 0) {
					System.out.println("�Խ��� ���� ����");
					mv.setViewName("error/error");
					mv.addObject("url",request.getRequestURL());
					mv.addObject("message", "���� ����");
					return mv;
				}
				
			// ����ó�� ������ ��� - �� ��� ���� ��û�� �����ϴ� �κ��Դϴ�.
				System.out.println("�Խ��� ���� ����");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('���� �Ǿ����ϴ�.');");
				out.println("location.href='BoardList.bo';");
				out.println("</script>");
				out.close();
				return null;
		}
		
		@GetMapping("BoardFileDown.no")
		public void BoardFileDown(String filename,HttpServletRequest request,String original,
				HttpServletResponse response) throws Exception
		{
			String savePath = "resources/upload";
			
			//������ ����ȯ������ ��� ��ü ����
			ServletContext context = request.getSession().getServletContext();
			String sDownloadPath = context.getRealPath(savePath);
			
			//String sFilePath = sDownloadPath + "\\" + filename;
			//���� ��� �������
			String sFilePath = sDownloadPath + "\\" + filename;
			System.out.println(sFilePath);
			
			byte b[] = new byte[4096];
			
			//sFilePath�� �ִ� ���� MimeType�� ���ؿɴϴ�.
			String sMimeType = context.getMimeType(sFilePath);
			System.out.println("sMimeType >>> " + sMimeType);
			
			if(sMimeType == null)
				sMimeType = "application/octet-stream";
			
			response.setContentType(sMimeType);
			
			//�ѱ� ���ϸ� ���� ����
			String sEncoding = new String(original.getBytes("utf-8"), "ISO-8859-1");
			System.out.println(sEncoding);
			
			response.setHeader("Content-Disposition","attachment; filename= " + sEncoding);
			
			try {
				//�� ����־��� ��� ��Ʈ�� ����
				BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
				//sFilePath�� ������ ���Ͽ� ���� �Է� ��Ʈ�� ����
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));
				
				int numRead;
				//read(b,0,b.length) : ����Ʈ �迭 b�� 0���� b.lenth
				//ũ�⸸ŭ �о�ɴϴ�.
				//���� �����Ͱ� �����ϸ�
				while((numRead = in.read(b, 0, b.length)) != -1) {
					//����Ʈ �迭 b�� 0������ numRead ũ�⸸ŭ �������� ���
					out2.write(b,0,numRead);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}















