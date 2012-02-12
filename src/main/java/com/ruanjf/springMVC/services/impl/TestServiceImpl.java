package com.ruanjf.springMVC.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruanjf.springMVC.dao.TestDao;
import com.ruanjf.springMVC.persistent.Test;
import com.ruanjf.springMVC.services.TestService;

@Service // 用于标注业务层组件
public class TestServiceImpl implements TestService {
	
	private TestDao testDao;
	@Resource
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public List<Test> getTests(){
		return testDao.find("from Test");
	}
	
	@Override
	public Test getTest(String id){
		return testDao.getById(id);
	}
	
	@Override
	public void addTest(Test test){
		testDao.save(test);
	}

}
