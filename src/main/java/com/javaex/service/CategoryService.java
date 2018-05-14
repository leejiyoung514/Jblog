package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	//카테고리 내용 추가 
	public CategoryVo insert(CategoryVo categoryVo) {
		int cateNo = categoryDao.insert(categoryVo);
		return categoryDao.selectCategory(cateNo);
	}
	
	//리스트 가져오기
	public List<CategoryVo> selectAll(String id){
		return categoryDao.selectAll(id);
	}
	
	//삭제하기
	public int delete(int cateNo) {
		
		return categoryDao.delete(cateNo);
	}
	
}
