package com.toy.mytoy.service;


import java.util.List;
import com.toy.mytoy.domain.Comment;

public interface CommentService {

	//댓글 등록
	//domain > Comment.java의 필드 num, id, content, reg_date > c로 담음	
	public int commentsInsert(Comment c);
	
	//댓글 수정
	public int commentsUpdate(Comment co);
	
	//댓글 삭제
	public int commentsDelete(int num);
	
	//댓글 개수
	public int getListCount(int num);
	
	//댓글 목록 불러오기 List<Comment> : 리턴 자료형 명시 List<생성됀 class명>
	public List<Comment> getCommentList(int board_num, int page);
}
