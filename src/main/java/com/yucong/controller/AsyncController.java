package com.yucong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.service.AsyncService;

@RestController
public class AsyncController {

	@Autowired
	private AsyncService asyncService;

	@RequestMapping("async1")
	public String async1() {
		System.out.println("async1前。。。");
		System.out.println("主线程：    " + Thread.currentThread().getName());
		asyncService.async1();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		System.out.println("async1后。。。");
		return "aaaaaaaaa";
	}

	@RequestMapping("async2")
	public String async2() {
		System.out.println("async2前。。。");
		asyncService.async2();
		System.out.println("async2后。。。");
		return "bbbbbbbbb";
	}

}
