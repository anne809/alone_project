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
	
	//1. 댓글 작성
	public int commentsInsert(Comment c) {
		return sqlSession.insert("Comments.insert",c);
	}
	
	//2. 댓글 수정
	public
	
	
	/*select 리스트는 두개의 인자만 받아옴. 쿼리문(Comments)과 파라미터값map >4가지 정보를 넘겨줄때쓰는건 가장 흔한 map) key와 value값
	implements 에서 (int board_num, int page) 글번호와 페이지값 받아서 
	map.put() 형식으로 넘김
	map.put("board_num", board_num); //해당하는 것을 받아서 이부분에넣습니다.
	map.put("page",page); //페이징 처리하는 공식에 넣습니다. 
	map.put("start",startrow);
	map.put("end",endrow);*/
	public List<Comment> getCommentList(Map<String, Integer> map){
		return sqlSession.selectList("Comments.getList", map);
	}
	
	
	
}
