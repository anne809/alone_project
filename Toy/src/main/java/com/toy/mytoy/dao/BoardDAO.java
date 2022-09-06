package com.toy.mytoy.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.toy.mytoy.domain.Board;

//DAO : DB�� �����Ͽ� �����͸� ��ȸ �Ǵ� �����ϴ� ��ü
@Repository //�����, DB�� ��ȸ �����ϴµ��� ������ �� ����(mapper���� ���� ����)
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	//root-context���� ���ƿ�
	
	//�� �ۼ�, ���
	public void insertBoard(Board board) {
		sqlsession.insert("Boards.insert", board);
	}
	//�� ����
	public int boardModify(Board modifyboard) {
		return sqlsession.update("Boards.modify",modifyboard);
	}
	//�� ����
	public int boardDelete(Board board) {
		return sqlsession.delete("Boards.delete",board);
	}
	
	//�� ���� ����
	public Board getDetail(int num) {
		return sqlsession.selectOne("Boards.Detail", num);
		//�۹�ȣ�� ��ȸ ���� ,���� ������
		//board.xml�� ���� ����� �ݴϴ�.
	}
	
	//�� �亯
	public int boardReply(Board board) {
		return sqlsession.insert("Boards.reply_insert",board);
	}
	//���� ���� ���ϱ�
	public int getListCount() {
		return sqlsession.selectOne("Boards.count");
	}
	
	//�� ��� ����
	public List<Board> getBoardList(HashMap<String, Integer> map){
		return sqlsession.selectList("Boards.list", map);
	}

	//��ȸ�� ������Ʈ
	public int setReadCountUpdate(int num) {
		return sqlsession.update("Boards.ReadCountUpdate",num);
		//board.xml
	}

	//�۾��� ���� Ȯ��
	public Board isBoardWriter(Map <String,Object> map) {
		return sqlsession.selectOne("Boards.BoardWriter", map);
	}
	
	//������ ����
	public int boardReplyUpdate(Board board) { 
		//�Ź� ������ ���̵� �ϳ��� ��� namespace ���� �����մϴ�.
		return sqlsession.update("reply_update",board);
	}
	
	//���� ������ ���� ���ϸ� �߰��ϴ� ��
	public int insert_deleteFile(String before_file) {
		return sqlsession.insert("Boards.insert_deleteFile", before_file);
	}
	
	public int insert_deleteFiles(Board board) {
		return sqlsession.insert("Boards.insert_deleteFiles",board);
	}
	
	//������ ���� ���
	public List<String> getDeleteFileList(){
		return sqlsession.selectList("Boards.deleteFileList");
	}
	
	
	
	
}
