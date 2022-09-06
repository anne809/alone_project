package com.toy.mytoy.dao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import com.toy.mytoy.domain.Comment;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//1. ��� �ۼ�
	public int commentsInsert(Comment c) {
		return sqlSession.insert("Comments.insert",c);
	}
	
	//2. ��� ����
	public
	
	
	/*select ����Ʈ�� �ΰ��� ���ڸ� �޾ƿ�. ������(Comments)�� �Ķ���Ͱ�map >4���� ������ �Ѱ��ٶ����°� ���� ���� map) key�� value��
	implements ���� (int board_num, int page) �۹�ȣ�� �������� �޾Ƽ� 
	map.put() �������� �ѱ�
	map.put("board_num", board_num); //�ش��ϴ� ���� �޾Ƽ� �̺κп��ֽ��ϴ�.
	map.put("page",page); //����¡ ó���ϴ� ���Ŀ� �ֽ��ϴ�. 
	map.put("start",startrow);
	map.put("end",endrow);*/
	public List<Comment> getCommentList(Map<String, Integer> map){
		return sqlSession.selectList("Comments.getList", map);
	}
	
	
	
}
