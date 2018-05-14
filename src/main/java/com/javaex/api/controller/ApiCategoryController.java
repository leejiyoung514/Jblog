package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.dao.CategoryDao;
import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;


@Controller
@RequestMapping(value = "{id}/admin")
public class ApiCategoryController {

    @Autowired
	private CategoryService categoryService;
    
    @ResponseBody
    @RequestMapping(value="/list", method=RequestMethod.POST)
    public List<CategoryVo> cateList(@PathVariable("id") String id){
    	System.out.println(id);
    	List<CategoryVo> list = categoryService.selectAll(id);
    	return list;
    }
    
	// 카테고리 내용 추가
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public CategoryVo category(@PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo) {
		System.out.println("controller 카테고리페이지");
		System.out.println(categoryVo.toString());
		return categoryService.insert(categoryVo);
	}
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam("cateNo") int cateNo) {
		int result=categoryService.delete(cateNo);
		return result;
	}
	
}
