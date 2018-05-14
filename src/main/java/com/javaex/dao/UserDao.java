package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원등록
	public int insert(UserVo userVo) {
		return sqlSession.insert("user.insert", userVo);
	}
	
	//아이디체크
	public String idCheck(String id) {
		return sqlSession.selectOne("user.idCheck", id);
	}
	
	//로그인-회원정보가져오기
	public UserVo getUser(String id, String password) {
		Map<String, String> userMap=new HashMap<String, String>();
		userMap.put("id", id);
		userMap.put("password", password);
		return sqlSession.selectOne("user.selectUserByidPw", userMap);
	}
	
}
