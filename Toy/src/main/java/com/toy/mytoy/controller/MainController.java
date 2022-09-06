package com.toy.mytoy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//�α��� �� ����� ���� ����
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//����� ���� 
import com.toy.mytoy.domain.Member;
import com.toy.mytoy.service.LoginMemberService;

@Controller
public class MainController {

	@Autowired
	private LoginMemberService loginmemberservice;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv)
			throws Exception {
		System.out.println("MainController_home ���Լ���");
		mv.setViewName("home");
		return mv;
	}
	
	// ����ȭ��
	@RequestMapping(value = "/main")
	public ModelAndView AfterLoginMain(ModelAndView mv, HttpSession session)
	{
		System.out.println("NEW_AfterLoginMain : ���� ");
		String id = (String)session.getAttribute("M_CODE");
	
		//���ǰ� ���� ��� ���ηΰ� ������ ��� �б�ó��
		if(id == null) {
			System.out.println("NEW_AfterLoginMain : ���ǰ� ���� ��� ");
			mv.setViewName("home");
			
		}else{
			
			System.out.println("NEW_AfterLoginMain : ID " + id);
			
			Member memberinfo = loginmemberservice.getInfo(id);
			
			System.out.println("NEW_AfterLoginMain : memberinfo " + memberinfo);
			System.out.println("NEW_AfterLoginMain : memberinfo.id " + memberinfo.getId());
			
			
			mv.setViewName("After_Login_home");
			mv.addObject("memberinfo",memberinfo);
			System.out.println("NEW_AfterLoginMain : ���� ");
		}
		
		return mv;
	}
}
