package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	// 글 삽입
	public int insert(PostVo postVo) {
		return sqlSession.insert("post.insert", postVo);
	}
	
	// 글 가져오기
	public PostVo getArticle(int postNo) {
		return sqlSession.selectOne("post.getPost", postNo);
	}
	
	// 글 리스트 가져오기
	public List<PostVo> getArticleList(int cateNo) {
		return sqlSession.selectList("post.getPostList", cateNo);
	}
}
