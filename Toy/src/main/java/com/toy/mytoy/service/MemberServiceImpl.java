package com.toy.mytoy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.mytoy.dao.MemberDAO;
import com.toy.mytoy.domain.Member;
//사용하려면 어노테이션 꼭 작성, 그리고 인터페이스 상속
//사용안함
//사용안함
//사용안함
//사용안함
//사용안함
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	/*
	@Override
	public int isEmail(String email) {
		Member rmember = dao.isEmail(email);//dao로 이동
		return(rmember == null) ? -1 : 1; // -1은 이메일이 존재하지 않는 경우
										  //  1은 아이디가 존재하는 경우
	}

	@Override
	public int isId(String id) {
		Member rmember = dao.isId(id);//dao로 이동
		return(rmember == null) ? -1 : 1; // -1은 이메일이 존재하지 않는 경우
										  //  1은 아이디가 존재하는 경우
	}

	@Override
	public int isId(String id, String pass) {
		Member rmember = dao.isId(id);
		int result= -1; // 아이디가 존재하지 않는 경우 - rmember가 null인 경우
		if(rmember !=null) { //아이디가 존재하는 경우
			if(rmember.getPassword().equals(pass)) {
				result=1; //아이디와 비밀번호가 일치하는 경우
			}else
				result=0; //아이디는 존재하지만 비밀번호가 일치하지 않는 경우
		}
		return result;
	}

	@Override
	public int insert(Member m) {
		return dao.insert(m);
	}*/

}
