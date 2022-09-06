package com.toy.mytoy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//로그인 및 사용자 정보 관련
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//사용자 정보 
import com.toy.mytoy.domain.Member;
import com.toy.mytoy.service.LoginMemberService;

@Controller
public class MainController {

	@Autowired
	private LoginMemberService loginmemberservice;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv)
			throws Exception {
		System.out.println("MainController_home 진입성공");
		mv.setViewName("home");
		return mv;
	}
	
	// 메인화면
	@RequestMapping(value = "/main")
	public ModelAndView AfterLoginMain(ModelAndView mv, HttpSession session)
	{
		System.out.println("NEW_AfterLoginMain : 초입 ");
		String id = (String)session.getAttribute("M_CODE");
	
		//세션값 없는 경우 메인로고 눌렀을 경우 분기처리
		if(id == null) {
			System.out.println("NEW_AfterLoginMain : 세션값 없는 경우 ");
			mv.setViewName("home");
			
		}else{
			
			System.out.println("NEW_AfterLoginMain : ID " + id);
			
			Member memberinfo = loginmemberservice.getInfo(id);
			
			System.out.println("NEW_AfterLoginMain : memberinfo " + memberinfo);
			System.out.println("NEW_AfterLoginMain : memberinfo.id " + memberinfo.getId());
			
			
			mv.setViewName("After_Login_home");
			mv.addObject("memberinfo",memberinfo);
			System.out.println("NEW_AfterLoginMain : 진입 ");
		}
		
		return mv;
	}
}
