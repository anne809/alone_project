package com.toy.mytoy.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�α��� �� ����� ���� ����
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.toy.mytoy.service.LoginMemberService;
//����� ���� 
import com.toy.mytoy.domain.Member;

/*
1. @Controller - ����� ��û�� �����ϴ� ��Ʈ�ѷ� Ŭ����
2. @Respository - ������ ���̽� ������ ó���ϴ� DAO Ŭ����
3. @Service - �����Ͻ� ������ ó���ϴ� Service Ŭ����

�帧 : MemberController > MemberService > MemberServiceImpl > MemberDAO
*
*
*/



@Controller
public class MemberController {
	
	//@Autowired
	//private MemberService memberservice;
	
	@Autowired
	private LoginMemberService loginmemberservice; // LoginMemberService �������̽� ����

	@Autowired // ��й�ȣ ��ȣȭ
	private PasswordEncoder passwordEncoder;

	//@Autowired
	//private CmtManageService cmtManageService;

	
	//�α���
	//���� index���������� login.net �α��� ȭ������ ���εǸ�
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv)
			throws Exception {
		System.out.println("MemberController_����");
		mv.setViewName("home");
		return mv;
	}
	
	
	//���� �α��� ������
	/*
	 * @RequestMapping(value = "/login.net", method = RequestMethod.GET) public
	 * ModelAndView Real_login(ModelAndView
	 * mv,@CookieValue(value="saveid",required=false)Cookie readCookie) throws
	 * Exception {
	 * 
	 * if(readCookie != null) { mv.addObject("saveid", readCookie.getValue());
	 * System.out.println("cookie time=" + readCookie.getMaxAge()); }
	 * 
	 * mv.setViewName("member/loginForm"); return mv; }
	 */
	
	//1. �α��� ȭ������ �̵�
	@GetMapping(value="/login.net")
	public String Real_login(Model m,@CookieValue(value="saveid",required=false)Cookie readCookie) {
		
		if(readCookie != null) {
			m.addAttribute("saveid", readCookie.getValue());
			System.out.println("cookie time=" + readCookie.getMaxAge());
		}
		//m.addAttribute("page","member/loginForm.jsp");
		m.addAttribute("page","member/loginForm.jsp");
		return "home";
	}
	
	//2. �α��� ó�� > loginForm.jsp ��û > /loginProcess.net
	//@RequestParam�� ������Ʈ�� ���� ���ڿ��� �޾ƿ��� ��Ȱ, required = false�� �����ϹǷμ� ���� �ʵ尪�� ������ ���� ����
	@RequestMapping(value="/contact_process.php",method=RequestMethod.POST)
	public String loginProcess(@RequestParam(value="M_CODE", required=false) String id,
			@RequestParam(value="M_PASS", required = false) String password,
			@RequestParam(value="remember", defaultValue="") String remember,
			HttpServletResponse response, HttpSession session) throws Exception{
		
		int result = loginmemberservice.isId(id, password); // 
		System.out.println("�α��� ��� = " + result);
		
		if(result == 1) {
			//���ǰ� �����ϱ�, "������ ���� ���̵�", ���ǿ� ���� ���̵�
			session.setAttribute("M_CODE",id);
			Cookie savecookie = new Cookie("savecode", id);
			if(!remember.equals("")) {
				//��Ű �ð� ����
				savecookie.setMaxAge(60 * 60 * 24);
				//������ ���
				System.out.println("��Ű���� : 60*60");
			}else {
				System.out.println("��Ű���� : 0");
				//���� �ȵ� ���
				//savecookie.setMaxAge(0);
			}
			//������ ��� ���� ���
			response.addCookie(savecookie);
			System.out.println("��Ű��� AfterloginMain���� �̵�");
			return "redirect:main";
		
		}else {
			String message = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			if(result == -1)
				message = "�����ȣ�� �������� �ʽ��ϴ�.";
	
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			//message ��´�
			out.println("alert('"+ message +"');");
			out.println("location.href='login.net';");
			out.println("</script>");
			return null;
		}
	}
	
	//3. ����
	//����� ���� ������
	@GetMapping(value="/insert.hr")
	public String joinForm(HttpServletRequest request, Model m) {
		m.addAttribute("page","member/joinForm.jsp");
		return "home";
	}
	
	//����� ������ > �ߺ� ���� �˻�(joinForm.jsp emailcheck.net)
	@RequestMapping(value = "/emailcheck.net", method = RequestMethod.GET)
	public void mailcheck(@RequestParam("email") String email, HttpServletResponse response) throws Exception {
		int result = loginmemberservice.isEmail(email); // > email���� ������ ��������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	//����� ������ > �̸�(���̵�)�ߺ� �˻�(joinForm.jsp idcheck.net)
	@RequestMapping(value = "/idcheck.net", method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception{
		int result = loginmemberservice.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	//����� ���� ó�� ����
	@RequestMapping(value="/joinProcess.net", method = RequestMethod.POST)
	public void joinProcess(Member member, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//��й�ȣ ��ȣȭ �߰�
		String encPassword = passwordEncoder.encode(member.getPassword());
		System.out.println(encPassword);
		member.setPassword(encPassword);
	
		int result = loginmemberservice.insert(member);
		
		out.println("<script>");
		
		//���� ó���� ���
		if(result == 1) {
			out.println("alert('��Ÿ�� ������ ������ �����մϴ�.');");
			out.println("location.href='home';");
		}else if(result == -1) {
			out.println("alert('���Կ� �����Ͽ����ϴ�.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
	
	//4. �α׾ƿ�
	  @RequestMapping(value="/logout.net", method=RequestMethod.GET) 
	  public ModelAndView logout(ModelAndView mv, HttpSession session) throws Exception{
		  session.invalidate();
		  mv.setViewName("member/check_session");
		  return mv;
	  }
	  
		//4. �α׾ƿ�
	 // @RequestMapping(value="/logout.net", method=RequestMethod.GET) 
	  //public String logout(HttpSession session) throws Exception{
	//	  session.invalidate();
	//	  return "redirect:home";
	 // }
}
