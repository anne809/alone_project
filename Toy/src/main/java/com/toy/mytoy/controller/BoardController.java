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
//우리 예전에 만들었던 BoardListAction 있잖아요? 그거 복붙하시면 되요. 기본 로직은 구하는건 거의 비슷해요...

@Controller
public class BoardController {

	@Autowired //서비스 계층 BoardService 주입
	private BoardService boardService;
	
	//첨부파일용
	@Value("{savefoldername}")
	private String saveFolder;

	@Autowired
	private LoginMemberService loginmemberservice;
	
	//글 목록 보기
	@RequestMapping(value="/BoardList.bo")
	public ModelAndView boardList(@RequestParam(value="page",defaultValue="1",
			required=false)int page, ModelAndView mv, Model m, HttpSession session) {
		
		int limit = 10; //한 화면에 출력할 레코드 갯수
		
		int listcount = boardService.getListCount(); //전체 글 개수 받아옴
		
		//총 페이지 수
		int maxpage = (listcount + limit -1 )/ limit; 
		//전체 글 개수 + 한화면 출력할 글개수 -1 / 한화면 출력할 글개수
		//100개+10개-1개 / 10개 = 109 / 10 = 10.9개
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21...)
		//요청파라미터 기본값 1로 시작 > startpage는 1
		int startpage = ((page -1) / 10 ) * 10 + 1;
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30...)
		int endpage = startpage + 10 - 1; //요청 파라미터 1로 세팅 > endpage는 10
		
		//만약 총페이지 수 보다 마지막 페이지 개수가 더 크다면, 
		if(endpage > maxpage)
			endpage = maxpage; //마지막페이지수까지 모두 노출
		
		//글목록 보기, page값 1, limit 레코드 개수 변수 넘겨서 쿼리 조회
		List<Board> boardlist = boardService.getBoardList(page, limit);
		
		//페이지 분할로 model 추가
		m.addAttribute("page", "board/qna_board_list.jsp");
		
		//mv.setViewName("board/qna_board_list");
		mv.setViewName("home");
		mv.addObject("page1", page); //jsp > page -> page1으로 변경
		mv.addObject("maxpage",maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);
		
		//로그인 되어 있는 경우
		String id = (String)session.getAttribute("M_CODE");
		if(id != null) {
			Member memberinfo = loginmemberservice.getInfo(id);
			mv.addObject("memberinfo",memberinfo);
		}
		
		return mv;
	}
	
	//글쓰기
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
	
	//글 저장하기
	@PostMapping(value = "Board_write_ok.no")
	public ModelAndView board_write_ok(Board board,HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:BoardList.bo");
		MultipartFile uploadfile = board.getUploadfile();
		
		//1.첨부파일 존재할시 로직
		if(!uploadfile.isEmpty()) {
			
			String fileName = uploadfile.getOriginalFilename();//원본 파일명
			board.setBOARD_FILE(fileName); //원본 파일명 저장
			
			 // 새로운 폴더 이름 : 오늘 - 년 - 월 - 일
	         Calendar c = Calendar.getInstance();
	         int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
	         int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
	         int date = c.get(Calendar.DATE); // 오늘 일 구합니다.
	         String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";
	         String homedir = saveFolder + year + "-" + month + "-" + date;
	         System.out.println(homedir);
	         File path1 = new File(homedir);
	         if (!(path1.exists())) {
	            path1.mkdirs(); // 새로운 폴더를 생성
	         }

	         // 난수를 구합니다.
	         Random r = new Random();
	         int random = r.nextInt(100000000);

	         /* 확장자 구하기 시작 */
	         int index = fileName.lastIndexOf(".");
	         // 문자열에서 특정 문자열의 위치 값 (index)를 반환한다.
	         // indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
	         // lastIndextOf는 마지막ㄷ으로 발견되는 문자열의 index를 반환합니다.
	         // (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
	         System.out.println("index = " + index);

	         String fileExtension = fileName.substring(index + 1);
	         System.out.println("fileExtension = " + fileExtension);
	         /* 확장자 구하기 끝 */

	         // 새로운 파일명
	         String refileName = "bbs" + year + month + date + random + "." + fileExtension;
	         System.out.println("refileName = " + refileName);

	         // 오라클 디비에 저장될 파일 명
	         String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
	         System.out.println("fileDBName = " + fileDBName);

	         // transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
	         uploadfile.transferTo(new File(saveFolder + fileDBName));
	         
	      // 바뀐 파일명 저장
	         board.setBOARD_FILE(fileDBName);
	         
			
		}
		//2. 글 저장 로직 //글 작성, 등록 서비스 > imple 전달
		boardService.insertBoard(board);
		
		return mv;
	}
	
		//글 상세보기
		@GetMapping("/BoardDetailAction.no")
		public ModelAndView Detail(int num, ModelAndView mv, HttpServletRequest request, Model m) {
			// 글 불러오는 로직 > 조회수 증가시키고 글내용 불러오는 과정
			Board board = boardService.getDetail(num);
			if (board == null) {
				System.out.println("상세보기 실패");
				m.addAttribute("page", "error/error_page.jsp");
				mv.setViewName("home");
				//전체 경로 가져오는 함수  http://localhost:8080/mytoy/boardlist.jsp
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "상세보기 실패입니다.");
			} else {
				System.out.println("글 상세보기 성공");
				m.addAttribute("page", "board/qna_board_view.jsp");
				mv.setViewName("home");
				mv.addObject("boarddata", board);
			}
			return mv;
		}
		
		
		//글 수정하기 1 (글 상세보기 로직과 거의 같음, 저장시 비밀번호 체크)
		@GetMapping("/BoardModifyView.no")
		public ModelAndView BoardModifyView
		(int num, ModelAndView mv,HttpServletRequest request,Model m) {
			Board boarddata = boardService.getDetail(num);
			if(boarddata == null) {
				System.out.println("(수정하기) 상세보기 실패");
				m.addAttribute("page", "error/error_page");
				mv.setViewName("home");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "(수정하기) 상세보기 실패입니다.");
				return mv;
			}
			System.out.println("(수정하기) 상세보기 성공");
			//이동할 페이지 속성값 담아주고
			m.addAttribute("page", "board/qna_board_modify.jsp");
			mv.setViewName("home");
			//데이터값도 보내준다.
			mv.addObject("boarddata", boarddata);
			
			return mv;
		}
		
		//글 수정하기 2(비밀번호 체크 로직 : 글번호와 비밀번호로만 체크)
		@PostMapping("BoardModifyAction.no")
		public ModelAndView BoardModifyAction
		(String before_file, Board board, String check, ModelAndView mv, HttpServletRequest request,
		HttpServletResponse response, Model m) throws Exception {
			//글번호와 비밀번호 매칭되는지 쿼리 조회
			int sample = board.getBOARD_NUM();
			String sample2 = board.getBOARD_PASS();
			System.out.println("글번호 출력 " + sample + "글 비밀번호 출력 " + sample2);
			
			boolean usercheck = boardService.isBoardWriter(board.getBOARD_NUM(), board.getBOARD_PASS());

			//비밀번호가 다를 경우
			if(usercheck == false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다.')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			
			//비밀번호가 맞는 경우 
			//첨부파일 수정 로직
			MultipartFile uploadfile = board.getUploadfile();
			String saveFolder = request.getSession().getServletContext().getRealPath("resources")+ "/upload/";
			
			//modifyform.js에서 파일명 체크값을 증감수로 넘겨줌
			System.out.println("check= "+check);
			
			//1. 기존 첨부파일 유지시
			if(check != null && !check.contentEquals("")) {
				board.setBOARD_ORIGINAL(check);
			}else { //2. 첨부파일 변경시
				if(uploadfile != null && !uploadfile.isEmpty()) {
					System.out.println("첨부파일이 변경되었습니다.");
					
					//원본 파일명
					String fileName = uploadfile.getOriginalFilename();
					//파일명 세팅
					board.setBOARD_ORIGINAL(fileName);
					// DB에 들어갈 파일명 가공해서 받아오는 로직
					String fileDBName = fileDBName(fileName,saveFolder);
					
					//transferTo(fILE path) : 업로드한 파일을 매개변수의 실제 경로에 저장
					uploadfile.transferTo(new File(saveFolder+fileDBName));
				
					//변경된 파일 명 > 도메인에 담기
					board.setBOARD_FILE(fileDBName);
				}else { //3. 첨부파일을 선택하지 않은 경우  uploadfile.isEmpty()
					System.out.println("선택한 첨부파일이 없습니다.");
					
					//빈 파일명으로 도메인 초기화("")해서 담기
					board.setBOARD_FILE("");
					board.setBOARD_ORIGINAL("");
				}
			}

			//값을 담은 도메인 board를 DAO의 메서드를 불러 쿼리에 반영해서 반환값 받아옴
			int result = boardService.boardModify(board);
			
			//1. 반영 실패시 에러페이지로 이동하도록 값 담는다
			if(result == 0) {
				System.out.println("글 수정 실패");
				mv.setViewName("error/error_page");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "글 수정 실패");
			}else {//2. 글 수정 성공
				System.out.println("글 수정 완료");
				
				// 수정전에 파일이 있고 새로운 파일을 선택한 경우는 삭제할 목록을 테이블에 추가합니다.
				//if(!before_file.equals("") && !before_file.equals(board.getEV_FILE())) {
				//	eventboardService.insert_deleteFile(before_file);
				//}
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 되었습니다.');");
				out.println("location.href="+"'BoardDetailAction.no?num="+board.getBOARD_NUM()+"';");
				out.println("</script>");
				out.close();
			}
			return mv;
		}
		
		// DB에 들어갈 내용 가공
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
				path1.mkdirs(); // 새로운 폴더를 생성, mkdir()은 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 생성 불가
				System.out.println("새로운 폴더 생성 성공");
			}

			Random r = new Random();
			int random = r.nextInt(100000000);

			// 확장자 구하기 시작
			int index = fileName.lastIndexOf(".");
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);

			// 새로운 파일명
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			System.out.println("refileName = " + refileName);

			// 오라클 디비에 저장될 파일 명
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);
			return fileDBName;
		}
		
		//글 삭제
		@RequestMapping(value = "/BoardDeleteAction.no")
		public ModelAndView BoardDeleteAction(String BOARD_PASS, int num,
				ModelAndView mv, 
				HttpServletResponse response,
				HttpServletRequest request
				) throws Exception{
			System.out.println("글삭제 num = "+ num);
			System.out.println("글삭제 BOARD_PASS= "+ BOARD_PASS);
			// 글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
			// 입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
			boolean usercheck = boardService.isBoardWriter(num, BOARD_PASS);
			
			// 비밀번호 일치하지 않은 경우
			if (usercheck == false) {
				System.out.println("usercheck false 진입 ");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			// 비밀번호 일치하는 경우 삭제 처리 합니다.
			int result = boardService.boardDelete(num);
			
			// 삭제처리 실패한 경우
				if(result == 0) {
					System.out.println("게시판 삭제 실패");
					mv.setViewName("error/error");
					mv.addObject("url",request.getRequestURL());
					mv.addObject("message", "삭제 실패");
					return mv;
				}
				
			// 삭제처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
				System.out.println("게시판 삭제 성공");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 되었습니다.');");
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
			
			//서블릿의 실행환경정보 담는 객체 리턴
			ServletContext context = request.getSession().getServletContext();
			String sDownloadPath = context.getRealPath(savePath);
			
			//String sFilePath = sDownloadPath + "\\" + filename;
			//파일 경로 만들어줌
			String sFilePath = sDownloadPath + "\\" + filename;
			System.out.println(sFilePath);
			
			byte b[] = new byte[4096];
			
			//sFilePath에 있는 파일 MimeType을 구해옵니다.
			String sMimeType = context.getMimeType(sFilePath);
			System.out.println("sMimeType >>> " + sMimeType);
			
			if(sMimeType == null)
				sMimeType = "application/octet-stream";
			
			response.setContentType(sMimeType);
			
			//한글 파일명 꺠짐 방지
			String sEncoding = new String(original.getBytes("utf-8"), "ISO-8859-1");
			System.out.println(sEncoding);
			
			response.setHeader("Content-Disposition","attachment; filename= " + sEncoding);
			
			try {
				//웹 브아주어의 출력 스트림 생성
				BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
				//sFilePath로 지정한 파일에 대한 입력 스트림 생성
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));
				
				int numRead;
				//read(b,0,b.length) : 바이트 배열 b의 0부터 b.lenth
				//크기만큼 읽어옵니다.
				//읽을 데이터가 존재하면
				while((numRead = in.read(b, 0, b.length)) != -1) {
					//바이트 배열 b의 0번부터 numRead 크기만큼 브라우저로 출력
					out2.write(b,0,numRead);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}















