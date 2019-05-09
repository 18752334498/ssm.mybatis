package com.yucong.service;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yucong.mapper.UserMapper;
import com.yucong.model.User;

@Service
//@EnableAsync
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;

	/**
	 * 
	 * @param id
	 */
	public void test1(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("===========调用test2==========");
		test2(id);// 此处是 this.test2(id)方法内部调用，不会走test2的事物
		System.out.println("===========调用test2==========");
		System.out.println(userMapper.selectUserById(id));
		logger.trace("======trace");
		logger.debug("======debug");
		logger.info("======info");
		logger.warn("======warn");
		logger.error("======error");
	}

	public void test2(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("=====================");
		System.out.println(userMapper.selectUserById(id));
	}

	public void test3(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("=====================");
		System.out.println(userMapper.updateAgeById(id));
		System.out.println("=====================");
		System.out.println(userMapper.selectUserById(id));
	}

	@Transactional
	public void test4(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("=====================");
		System.out.println(userMapper.updateAgeById(id));
		System.out.println("=====================");
		System.out.println(userMapper.selectUserById(id));
		int a = 1 / 0;
	}

	/**************************************************************************************************/

	// 内部方法调用不会触发动态代理（@Transactional @Async）
	// @Transactional 和 @Async 同时使用时不会回滚

	@Async("transactionalExecutor") // 如果此业务是附加业务不能干扰主页物，则需要异步执行
	@Transactional // 内部方法调用不会触发动态代理，包括@Async
	public void test5(int id) {
		System.out.println(userMapper.updateAgeById(1));
		try {
			System.out.println("异步线程名称： " + Thread.currentThread().getName());
			Thread.sleep(2000);
			System.out.println("睡眠结束。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		int a = 1 / 0;
	}

	@Transactional
	public void test6(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("=====================");
		System.out.println(userMapper.updateAgeById(id));
		System.out.println("==========调用test5===========");
		try {
			// 获取当前对象的动态代理
			UserService currentProxy = (UserService) AopContext.currentProxy();
			currentProxy.test5(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==========调用test5===========");
		System.out.println(userMapper.selectUserById(id));
		System.out.println(userMapper.selectUserById(id));
	}

	@Transactional
	public void test7(int id) {
		System.out.println(userMapper.selectUserById(id));
		System.out.println("=====================");
		System.out.println(userMapper.updateAgeById(id));
		System.out.println("==========调用test5===========");
		// 获取当前对象的动态代理
		UserService currentProxy = (UserService) AopContext.currentProxy();
		currentProxy.test5(id);
		System.out.println("==========调用test5===========");
		System.out.println(userMapper.selectUserById(id));
	}

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/*
	 * 批量插入
	 */
	public void insertBatch() {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = null;
		for (int i = 1; i <= 12; i++) {
			user = new User();
			user.setId(i);
			user.setName("Tom-" + i);
			user.setAge(i);
			Integer count = mapper.insertBatch(user);
			if (i % 5 == 0) {
				sqlSession.commit();
				System.out.println(count);
			}
		}
		sqlSession.commit();
	}

	/*
	 * 批量插入
	 */
	public void insertBatch2() {
		User user = null;
		for (int i = 15; i <= 18; i++) {
			user = new User();
			user.setId(i);
			user.setName("Tom-" + i);
			user.setAge(i);
			Integer one = userMapper.insertOneByOne(user);
			System.out.println(one);
		}
	}
}
