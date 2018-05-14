package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;

@Controller
@RequestMapping(value = "{id}")
public class ApiCommentController {

	@Autowired
	private CommentService commentService;
	
	
	@ResponseBody
	@RequestMapping(value = "/list/{postNo}", method = RequestMethod.POST)
	public List<CommentVo> cateList(@PathVariable("id") String id, 
			@PathVariable("postNo") int postNo) {
		System.out.println("글번호 넘어와죠:"+postNo);
		 List<CommentVo> list = commentService.selectAll( postNo);
		System.out.println("댓글전체리스트출력: "+list.toString());
		 return list;
	}

	// 코멘트 내용추가
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public CommentVo Comment(@PathVariable("id") String id, @ModelAttribute CommentVo commentVo) {
		System.out.println("controller 코멘트페이지");
		System.out.println("$코멘트내용추가뭐냐?"+commentVo.toString());
		return commentService.insert(commentVo);
	}

	// 코멘트 삭제
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestParam("cmtNo") int cmtNo) {
		System.out.println("삭제넘버 넘어오니? "+cmtNo);
		return commentService.delete(cmtNo);
	}

}
