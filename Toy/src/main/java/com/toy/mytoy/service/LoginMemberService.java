package com.toy.mytoy.service;

import java.util.List;
import com.toy.mytoy.domain.Member;

//�������̽��� ���� ��ɸ� ����
//�������̽��� ���� ��ɸ� ����
//�������̽��� ���� ��ɸ� ����

//����������Ʈ - �α��� ��� ����
//service > LoginMemberServiceImp ���� ��ӹ޾� ����
/*
 1. Service ���� 
 : ��Ʈ�ѷ��� ȣ�⿡ ���� ������ DAO�� ��� ȣ�����ִ� �߰� �ܰ��� ��ü.
     �� �������� ��Ʈ�ѷ����� ���޹��� ���� DAO�� �����ϱ� ���� �Ķ���͸� �����ϰų�,
   Controller > Service > DAO
   DAO > Service > Controller
   DAO�κ��� ���Ϲ��� ���� ��Ʈ�ѷ����� �����ϱ� ���� ���� �����Ҽ� �ֽ��ϴ�.
   �̷��� ó���� �����Ͻ� �����̶�� �մϴ�.

  2. �����Ͻ� ������ ��Ʈ�ѷ��� Ŭ���̾�Ʈ ��û�� �ĺ�, ������ �����ϴ� ���ҿ� �����Ҽ� �ֵ���
  ������ ������ �ʿ��� ����ó���� �и��س� ����
  
  3. Service ������ �����ϰ��� �ϴ� ����� ����� Interface ���·� �����ϰ�
  	����ü Ŭ������ ������ �ξ� �����մϴ�.
 * */


public interface LoginMemberService {

	public int isId(String id);
	public int isId(String id, String pass); //isEmailOrId
	public int isEmail(String email);	
	public int insert(Member m);
	public Member getInfo(String m_code);
	public int update(Member m);
	public int update_admin(Member m);
	public int update_res(Member member);
	
	//public List<Member> getSearchList2(int index, String search_word, int page, int limit);
	//public List<Member> resSearchList(int index, String search_word, int page, int limit);
	//public int getSearchListCount(int index, String search_word);

	
}
