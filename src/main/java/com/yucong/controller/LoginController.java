package com.yucong.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yucong.model.Users;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("form")
	public String login() {
		System.out.println("登陆。。。。!!!!!!!");
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public String validate(Users users) {
		users.setId(33);
		System.out.println(users);

		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "aa");
		map.put("2", "bb");
		map.put("3", "cc");
		return JSON.toJSONString(map);
	}

}
