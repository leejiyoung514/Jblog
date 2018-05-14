package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	private SqlSession sqlSession;
	
	//댓글전체리스트
	public List<CommentVo> selectAll( int postNo){
			return sqlSession.selectList("comment.selectByCommentList", postNo);
	}

	//댓글저장
	public int insert(CommentVo commentVo) {
		 sqlSession.insert("comment.commentInsert", commentVo);
		 return commentVo.getCmtNo();
	}
	
	//댓글 가져오기
	public CommentVo selectComment(int cmtNo) {
		return sqlSession.selectOne("comment.selectComment", cmtNo);
	}

	//삭제
    public int delete(int cmtNo) {
    	return sqlSession.delete("comment.delete", cmtNo);
    }
}
