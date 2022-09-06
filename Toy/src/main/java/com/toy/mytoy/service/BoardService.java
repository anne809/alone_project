package com.toy.mytoy.service;

import java.util.List;
import com.toy.mytoy.domain.Board;


//�������̽��� ���� ��ɸ� ����
//�������̽��� ���� ��ɸ� ����
//�������̽��� ���� ��ɸ� ����

//����������Ʈ - �α��� ��� ����
//service > BoardServiceImp ���� ��ӹ޾� ����
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
public interface BoardService {

	//�� �ۼ�, ���
	public void insertBoard(Board board);

	//�� ����
	public int boardModify(Board modifyboard);
	
	//�� ����
	public int boardDelete(int num);

	
	
	
	//�� ���� ����
	//import com.toy.mytoy.domain.Board;
	public Board getDetail(int num);
	
	//�� �亯
	//import com.toy.mytoy.domain.Board;
	public int boardReply(Board board);

	//���� ���� ���ϱ�
	public int getListCount();
	
	//�� ��� ����
	public List<Board> getBoardList(int page, int limit);
	
	//��ȸ�� ������Ʈ
	public int setReadCountUpdate(int num);
	
	//�۾������� Ȯ��
	public boolean isBoardWriter(int num, String pass);
	
	//������ ����
	public int boardReplyUpdate(Board board);
	
	//���� ������ ���� ���ϸ� �߰��ϴ� ��
	public int insert_deleteFile(String before_file);
	
	//������ ���� ���
	public List<String> getDetailFileList();
}
