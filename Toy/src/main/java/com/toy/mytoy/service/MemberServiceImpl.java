package com.toy.mytoy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.mytoy.dao.MemberDAO;
import com.toy.mytoy.domain.Member;
//����Ϸ��� ������̼� �� �ۼ�, �׸��� �������̽� ���
//������
//������
//������
//������
//������
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	/*
	@Override
	public int isEmail(String email) {
		Member rmember = dao.isEmail(email);//dao�� �̵�
		return(rmember == null) ? -1 : 1; // -1�� �̸����� �������� �ʴ� ���
										  //  1�� ���̵� �����ϴ� ���
	}

	@Override
	public int isId(String id) {
		Member rmember = dao.isId(id);//dao�� �̵�
		return(rmember == null) ? -1 : 1; // -1�� �̸����� �������� �ʴ� ���
										  //  1�� ���̵� �����ϴ� ���
	}

	@Override
	public int isId(String id, String pass) {
		Member rmember = dao.isId(id);
		int result= -1; // ���̵� �������� �ʴ� ��� - rmember�� null�� ���
		if(rmember !=null) { //���̵� �����ϴ� ���
			if(rmember.getPassword().equals(pass)) {
				result=1; //���̵�� ��й�ȣ�� ��ġ�ϴ� ���
			}else
				result=0; //���̵�� ���������� ��й�ȣ�� ��ġ���� �ʴ� ���
		}
		return result;
	}

	@Override
	public int insert(Member m) {
		return dao.insert(m);
	}*/

}
