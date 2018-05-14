package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UsersService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UsersService userService;

	// 회원가입페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinform() {
		return "user/joinForm";
	}

	// 회원가입등록
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "user/joinSuccess";								
	}
	
	// 아이디체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public Boolean isExist(@RequestParam("id") String id) {
		Boolean isExist = userService.idCheck(id);
		return isExist;
	}

	// 로그인페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginform() {
		return "user/loginForm";
	}

	// 로그인하기
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
		UserVo authUser = userService.login(id, password);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			return "redirect:/user/login?result=fail";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}

}
