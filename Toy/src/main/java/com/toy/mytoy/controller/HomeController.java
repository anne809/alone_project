package com.toy.mytoy.controller;

import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
//������ home.jsp ���
//������ home.jsp ���
//������ home.jsp ���
//������ home.jsp ���
//������
//@Controller
public class HomeController {
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Controller ���Լ���");
		return "home";
	}*/
	
	
	@GetMapping(value = "error")
	public String error() {		
		return "error/error";
	}
}

