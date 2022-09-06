package com.toy.mytoy.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.toy.mytoy.domain.Board;

//DAO : DB에 접근하여 데이터를 조회 또는 조작하는 객체
@Repository //저장소, DB를 조회 조작하는데에 중점을 둔 개념(mapper보다 상위 개념)
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	//root-context에서 날아옴
	
	//글 작성, 등록
	public void insertBoard(Board board) {
		sqlsession.insert("Boards.insert", board);
	}
	//글 수정
	public int boardModify(Board modifyboard) {
		return sqlsession.update("Boards.modify",modifyboard);
	}
	//글 삭제
	public int boardDelete(Board board) {
		return sqlsession.delete("Boards.delete",board);
	}
	
	//글 내용 보기
	public Board getDetail(int num) {
		return sqlsession.selectOne("Boards.Detail", num);
		//글번호로 조회 쿼리 ,내용 가져옴
		//board.xml로 가서 만들어 줍니다.
	}
	
	//글 답변
	public int boardReply(Board board) {
		return sqlsession.insert("Boards.reply_insert",board);
	}
	//글의 갯수 구하기
	public int getListCount() {
		return sqlsession.selectOne("Boards.count");
	}
	
	//글 목록 보기
	public List<Board> getBoardList(HashMap<String, Integer> map){
		return sqlsession.selectList("Boards.list", map);
	}

	//조회수 업데이트
	public int setReadCountUpdate(int num) {
		return sqlsession.update("Boards.ReadCountUpdate",num);
		//board.xml
	}

	//글쓴이 인지 확인
	public Board isBoardWriter(Map <String,Object> map) {
		return sqlsession.selectOne("Boards.BoardWriter", map);
	}
	
	//시퀀스 수정
	public int boardReplyUpdate(Board board) { 
		//매버 파일중 아이디가 하나인 경우 namespace 생략 가능합니다.
		return sqlsession.update("reply_update",board);
	}
	
	//파일 삭제를 위한 파일명 추가하는 곳
	public int insert_deleteFile(String before_file) {
		return sqlsession.insert("Boards.insert_deleteFile", before_file);
	}
	
	public int insert_deleteFiles(Board board) {
		return sqlsession.insert("Boards.insert_deleteFiles",board);
	}
	
	//삭제할 파일 목록
	public List<String> getDeleteFileList(){
		return sqlsession.selectList("Boards.deleteFileList");
	}
	
	
	
	
}
