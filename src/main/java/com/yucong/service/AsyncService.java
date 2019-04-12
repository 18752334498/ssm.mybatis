package com.yucong.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsyncService {

	@Async("asyncExecutor")
	public void async1() {
		try {
			System.out.println("异步线程池，开始睡眠4秒。。。" + Thread.currentThread().getName());
			Thread.sleep(4000);
			System.out.println("异步线程池，睡眠结束。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 必须加上 @Transactional 否则会报错
	 * <p>
	 * Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to
	 * make it available
	 * </p>
	 * 
	 * @Transactional 是为了增强此方法
	 */
	@Transactional
	public void async2() {
		try {
			System.out.println("主业务方法，开始睡眠4秒。。。" + Thread.currentThread().getName());
			System.out.println("开始调用异步业务。。。");

			AsyncService asyncService = (AsyncService) AopContext.currentProxy();
			asyncService.async1();

			System.out.println("结束调用异步业务。。。");
			Thread.sleep(2000);
			System.out.println("主业务方法，睡眠结束。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
