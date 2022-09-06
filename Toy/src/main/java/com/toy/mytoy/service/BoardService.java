package com.toy.mytoy.service;

import java.util.List;
import com.toy.mytoy.domain.Board;


//인터페이스로 선언 기능만 존재
//인터페이스로 선언 기능만 존재
//인터페이스로 선언 기능만 존재

//토이프로젝트 - 로그인 멤버 서비스
//service > BoardServiceImp 에서 상속받아 구현
/*
1. Service 계층 
: 컨트롤러의 호출에 따라 적합한 DAO를 대신 호출해주는 중간 단계의 객체.
   이 과정에서 컨트롤러부터 전달받은 값을 DAO에 전달하기 전에 파라미터를 가공하거나,
 Controller > Service > DAO
 DAO > Service > Controller
 DAO로부터 리턴받은 값을 컨트롤러에게 리턴하기 전에 값을 가공할수 있습니다.
 이러한 처리를 비지니스 로직이라고 합니다.

2. 비지니스 로직은 컨트롤러가 클라이언트 요청을 식별, 응답을 구성하는 역할에 집중할수 있도록
데이터 연동에 필요한 각종처리를 분리해낸 형태

3. Service 계층은 구현하고자 하는 기능의 목록을 Interface 형태로 나열하고
	구현체 클래스를 별도로 두어 구성합니다.
* */
public interface BoardService {

	//글 작성, 등록
	public void insertBoard(Board board);

	//글 수정
	public int boardModify(Board modifyboard);
	
	//글 삭제
	public int boardDelete(int num);

	
	
	
	//글 내용 보기
	//import com.toy.mytoy.domain.Board;
	public Board getDetail(int num);
	
	//글 답변
	//import com.toy.mytoy.domain.Board;
	public int boardReply(Board board);

	//글의 갯수 구하기
	public int getListCount();
	
	//글 목록 보기
	public List<Board> getBoardList(int page, int limit);
	
	//조회수 업데이트
	public int setReadCountUpdate(int num);
	
	//글쓴이인지 확인
	public boolean isBoardWriter(int num, String pass);
	
	//시퀀스 수정
	public int boardReplyUpdate(Board board);
	
	//파일 삭제를 위한 파일명 추가하는 곳
	public int insert_deleteFile(String before_file);
	
	//삭제할 파일 목록
	public List<String> getDetailFileList();
}
