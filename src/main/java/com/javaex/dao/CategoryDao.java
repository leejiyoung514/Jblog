package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	// 블로그 카테고리 생성
	public int insert(CategoryVo categoryVo) {
		sqlSession.insert("category.categoryInsert", categoryVo);
		return categoryVo.getCateNo();
	}

	//카테고리 리스트 가져오기
	public CategoryVo selectCategory(int cateNo) {
		return sqlSession.selectOne("category.selectCategory", cateNo);
	}
	
	//카테고리 리스트 전체 가져오기
	public List<CategoryVo> selectAll(String id){
		return sqlSession.selectList("category.selectByCateList",id);
	}

	//삭제하기 
	public int delete(int cateNo) {
		return sqlSession.delete("category.delete", cateNo);
	}
	
}
