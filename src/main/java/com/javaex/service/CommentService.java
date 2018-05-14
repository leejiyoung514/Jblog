package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	//리스트 가져오기
	public List<CommentVo> selectAll(int postNo){
		return commentDao.selectAll( postNo);
	}
	
	// 카테고리 내용 추가
	public CommentVo insert(CommentVo commentVo) {
		int cmtNo = commentDao.insert(commentVo);
		System.out.println(cmtNo + "이다");
		return commentDao.selectComment(cmtNo);
	}
	
	
	// 삭제하기
	public int delete(int cmtNo) {
		return commentDao.delete(cmtNo);
	}
}
