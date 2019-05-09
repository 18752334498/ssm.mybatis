package com.yucong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yucong.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/test1")
	public void test1(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.test1(id);
	}

	/**
	 * 可以理解为service里的一个method就是一次sqlsession
	 * 
	 * @param request
	 */
	@RequestMapping("/test2")
	public void test2(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.test2(id); // Creating a new SqlSession
		System.out.println("同一个controller第二次调用service。。。。。。。。。。");
		userService.test2(id); // Creating a new SqlSession
	}

	@RequestMapping("/test3")
	public void test3(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.test3(id);
	}

	@RequestMapping("/test4")
	public void test4(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.test4(id);
	}

	@RequestMapping("/test6")
	public void test6(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.test6(id);
	}

	@RequestMapping("/test7")
	public void test7(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.test7(id);
	}

	@RequestMapping("/insertBatch")
	public void insertBatch(HttpServletRequest request) {
		userService.insertBatch();
		userService.insertBatch2();
	}

}
