package com.toy.mytoy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.toy.mytoy.domain.Board;//조작 또는 기능
import com.toy.mytoy.dao.BoardDAO;//sql 조작

/*인터페이스 Service 상속 받아서 overrride 매개변수, 리턴 모두 부모와 동일하게
 dao와 domain > import
 
 Controller > Service > DAO
 DAO > Service > Controller
*/
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;

	// 글 작성, 등록
	@Override
	public void insertBoard(Board board) {
		dao.insertBoard(board);

	}

	// 글 수정 > BoardDAO(SQL 조작클래스)
	@Override
	public int boardModify(Board modifyboard) {
		return dao.boardModify(modifyboard);
	}

	// 글 삭제(글번호로 조회후, 파일명 저장> 글삭제 쿼리 진행)
	@Override
	public int boardDelete(int num) {
		int result = 0;
		// 우선 글번호로 글이 존재하는지 BoardDAO(SQL 조작클래스) 조회
		Board board = dao.getDetail(num);
		System.out.println("BoardService imple 진입 " + board);
		if (board != null) { // 글이 존재한다면

			// 추가 : 삭제할 파일 목록들을 저장하기 위한 메서드 호출
			dao.insert_deleteFiles(board);
			result = dao.boardDelete(board);
		}
		return result;
	}

	// 글 내용 보기
	@Override
	public Board getDetail(int num) {
		// 1.내부 함수 > 조회수 증가(여기서 작업방해 없도록 @트랜잭션 사용함)
		if (setReadCountUpdate(num) != 1)// 조회수 업데이트 쿼리 결과값이 1 아니라면
			return null;
		// 2.글 조회 쿼리
		return dao.getDetail(num);
	}

	// 글 답변
	@Override
	public int boardReply(Board board) {
		//시퀀스 증가 업데이트
		boardReplyUpdate(board);
		//domai.board 증가
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		return dao.boardReply(board);
	}

	// 글의 갯수 구하기
	//select count(*) from board
	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	// 글 목록 보기
	@Override
	public List<Board> getBoardList(int page, int limit) {
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start",startrow);
		map.put("end",endrow);
		
		return dao.getBoardList(map);
	}

	// 조회수 업데이트
	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	// 글쓴이 인지 확인
	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		//select * 
		//from board
		//where BOARD_NUM=#{num} and BOARD_PASS=#{pass}
		//글번호가 같고 글의 비밀번호가 동일한 경우
		//true를 반환
		Board result = dao.isBoardWriter(map);
		if(result == null)
			return false;
		else
			return true;
	}

	// 댓글 업데이트, 시퀀스값 업데이트 쿼리
	@Override
	public int boardReplyUpdate(Board board) {
		return dao.boardReplyUpdate(board);
	}

	// 파일 삭제를 위한 파일명 추가하는곳
	@Override
	public int insert_deleteFile(String before_file) {
		//Insert into delete_file
		//values(#{file})
		//파일명 추가 쿼리
		return dao.insert_deleteFile(before_file);
	}

	// 삭제할 파일 목록
	@Override
	public List<String> getDetailFileList() {
		//select board_file 
		//from delete_file
		//삭제할 파일 조회 쿼리
		return dao.getDeleteFileList();
	}

}
