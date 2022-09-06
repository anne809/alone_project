package com.toy.mytoy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.toy.mytoy.domain.Board;//���� �Ǵ� ���
import com.toy.mytoy.dao.BoardDAO;//sql ����

/*�������̽� Service ��� �޾Ƽ� overrride �Ű�����, ���� ��� �θ�� �����ϰ�
 dao�� domain > import
 
 Controller > Service > DAO
 DAO > Service > Controller
*/
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;

	// �� �ۼ�, ���
	@Override
	public void insertBoard(Board board) {
		dao.insertBoard(board);

	}

	// �� ���� > BoardDAO(SQL ����Ŭ����)
	@Override
	public int boardModify(Board modifyboard) {
		return dao.boardModify(modifyboard);
	}

	// �� ����(�۹�ȣ�� ��ȸ��, ���ϸ� ����> �ۻ��� ���� ����)
	@Override
	public int boardDelete(int num) {
		int result = 0;
		// �켱 �۹�ȣ�� ���� �����ϴ��� BoardDAO(SQL ����Ŭ����) ��ȸ
		Board board = dao.getDetail(num);
		System.out.println("BoardService imple ���� " + board);
		if (board != null) { // ���� �����Ѵٸ�

			// �߰� : ������ ���� ��ϵ��� �����ϱ� ���� �޼��� ȣ��
			dao.insert_deleteFiles(board);
			result = dao.boardDelete(board);
		}
		return result;
	}

	// �� ���� ����
	@Override
	public Board getDetail(int num) {
		// 1.���� �Լ� > ��ȸ�� ����(���⼭ �۾����� ������ @Ʈ����� �����)
		if (setReadCountUpdate(num) != 1)// ��ȸ�� ������Ʈ ���� ������� 1 �ƴ϶��
			return null;
		// 2.�� ��ȸ ����
		return dao.getDetail(num);
	}

	// �� �亯
	@Override
	public int boardReply(Board board) {
		//������ ���� ������Ʈ
		boardReplyUpdate(board);
		//domai.board ����
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		return dao.boardReply(board);
	}

	// ���� ���� ���ϱ�
	//select count(*) from board
	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	// �� ��� ����
	@Override
	public List<Board> getBoardList(int page, int limit) {
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start",startrow);
		map.put("end",endrow);
		
		return dao.getBoardList(map);
	}

	// ��ȸ�� ������Ʈ
	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	// �۾��� ���� Ȯ��
	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		//select * 
		//from board
		//where BOARD_NUM=#{num} and BOARD_PASS=#{pass}
		//�۹�ȣ�� ���� ���� ��й�ȣ�� ������ ���
		//true�� ��ȯ
		Board result = dao.isBoardWriter(map);
		if(result == null)
			return false;
		else
			return true;
	}

	// ��� ������Ʈ, �������� ������Ʈ ����
	@Override
	public int boardReplyUpdate(Board board) {
		return dao.boardReplyUpdate(board);
	}

	// ���� ������ ���� ���ϸ� �߰��ϴ°�
	@Override
	public int insert_deleteFile(String before_file) {
		//Insert into delete_file
		//values(#{file})
		//���ϸ� �߰� ����
		return dao.insert_deleteFile(before_file);
	}

	// ������ ���� ���
	@Override
	public List<String> getDetailFileList() {
		//select board_file 
		//from delete_file
		//������ ���� ��ȸ ����
		return dao.getDeleteFileList();
	}

}
