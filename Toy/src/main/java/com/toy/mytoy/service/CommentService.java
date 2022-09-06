package com.toy.mytoy.service;


import java.util.List;
import com.toy.mytoy.domain.Comment;

public interface CommentService {

	//��� ���
	//domain > Comment.java�� �ʵ� num, id, content, reg_date > c�� ����	
	public int commentsInsert(Comment c);
	
	//��� ����
	public int commentsUpdate(Comment co);
	
	//��� ����
	public int commentsDelete(int num);
	
	//��� ����
	public int getListCount(int num);
	
	//��� ��� �ҷ����� List<Comment> : ���� �ڷ��� ��� List<������ class��>
	public List<Comment> getCommentList(int board_num, int page);
}
