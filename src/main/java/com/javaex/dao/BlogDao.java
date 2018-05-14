package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	// 블로그 생성
    public int insert(BlogVo blogVo) {
    	return sqlSession.insert("blog.blogInsert", blogVo);
    }
    
    // 기본 설정 업데이트
    public int update(BlogVo blogVo) {
    	return sqlSession.update("blog.blogUpdate", blogVo);
    }
    
    //블로그 리스트 가져오기
    public BlogVo getBlogList(String id){
    	return sqlSession.selectOne("blog.getBlogList", id);
    }
    
    
}
