package com.toy.mytoy.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//로그인 및 사용자 정보 관련
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
//사용자 정보 
import com.toy.mytoy.domain.Member;

/*
1. @Controller - 사용자 요청을 제어하는 컨트롤러 클래스
2. @Respository - 데이터 베이스 연동을 처리하는 DAO 클래스
3. @Service - 비지니스 로직을 처리하는 Service 클래스

흐름 : MemberController > MemberService > MemberServiceImpl > MemberDAO
*
*
*/



@Controller
public class MemberController {
	
	//@Autowired
	//private MemberService memberservice;
	
	@Autowired
	private LoginMemberService loginmemberservice; // LoginMemberService 인터페이스 주입

	@Autowired // 비밀번호 암호화
	private PasswordEncoder passwordEncoder;

	//@Autowired
	//private CmtManageService cmtManageService;

	
	//로그인
	//최초 index페이지에서 login.net 로그인 화면으로 매핑되면
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv)
			throws Exception {
		System.out.println("MemberController_진입");
		mv.setViewName("home");
		return mv;
	}
	
	
	//실제 로그인 페이지
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
	
	//1. 로그인 화면으로 이동
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
	
	//2. 로그인 처리 > loginForm.jsp 요청 > /loginProcess.net
	//@RequestParam로 쿼리스트링 변수 문자열값 받아오는 역활, required = false를 지정하므로서 만약 필드값이 없을때 오류 방지
	@RequestMapping(value="/contact_process.php",method=RequestMethod.POST)
	public String loginProcess(@RequestParam(value="M_CODE", required=false) String id,
			@RequestParam(value="M_PASS", required = false) String password,
			@RequestParam(value="remember", defaultValue="") String remember,
			HttpServletResponse response, HttpSession session) throws Exception{
		
		int result = loginmemberservice.isId(id, password); // 
		System.out.println("로그인 결과 = " + result);
		
		if(result == 1) {
			//세션값 설정하기, "설정한 세션 아이디", 세션에 넣을 아이디값
			session.setAttribute("M_CODE",id);
			Cookie savecookie = new Cookie("savecode", id);
			if(!remember.equals("")) {
				//쿠키 시간 설정
				savecookie.setMaxAge(60 * 60 * 24);
				//저장한 경우
				System.out.println("쿠키저장 : 60*60");
			}else {
				System.out.println("쿠키저장 : 0");
				//저장 안된 경우
				//savecookie.setMaxAge(0);
			}
			//마지막 모든 값을 담고
			response.addCookie(savecookie);
			System.out.println("쿠키담고 AfterloginMain으로 이동");
			return "redirect:main";
		
		}else {
			String message = "비밀번호가 일치하지 않습니다.";
			if(result == -1)
				message = "사원번호가 존재하지 않습니다.";
	
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			//message 담는다
			out.println("alert('"+ message +"');");
			out.println("location.href='login.net';");
			out.println("</script>");
			return null;
		}
	}
	
	//3. 가입
	//멤버쉽 가입 페이지
	@GetMapping(value="/insert.hr")
	public String joinForm(HttpServletRequest request, Model m) {
		m.addAttribute("page","member/joinForm.jsp");
		return "home";
	}
	
	//멤버십 가입폼 > 중복 메일 검사(joinForm.jsp emailcheck.net)
	@RequestMapping(value = "/emailcheck.net", method = RequestMethod.GET)
	public void mailcheck(@RequestParam("email") String email, HttpServletResponse response) throws Exception {
		int result = loginmemberservice.isEmail(email); // > email값을 던져서 돌려받음
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	//멤버십 가입폼 > 이름(아이디)중복 검사(joinForm.jsp idcheck.net)
	@RequestMapping(value = "/idcheck.net", method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception{
		int result = loginmemberservice.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	//멤버십 가입 처리 로직
	@RequestMapping(value="/joinProcess.net", method = RequestMethod.POST)
	public void joinProcess(Member member, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//비밀번호 암호화 추가
		String encPassword = passwordEncoder.encode(member.getPassword());
		System.out.println(encPassword);
		member.setPassword(encPassword);
	
		int result = loginmemberservice.insert(member);
		
		out.println("<script>");
		
		//정상 처리된 경우
		if(result == 1) {
			out.println("alert('몬타나 리워드 가입을 축하합니다.');");
			out.println("location.href='home';");
		}else if(result == -1) {
			out.println("alert('가입에 실패하였습니다.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
	
	//4. 로그아웃
	  @RequestMapping(value="/logout.net", method=RequestMethod.GET) 
	  public ModelAndView logout(ModelAndView mv, HttpSession session) throws Exception{
		  session.invalidate();
		  mv.setViewName("member/check_session");
		  return mv;
	  }
	  
		//4. 로그아웃
	 // @RequestMapping(value="/logout.net", method=RequestMethod.GET) 
	  //public String logout(HttpSession session) throws Exception{
	//	  session.invalidate();
	//	  return "redirect:home";
	 // }
}
