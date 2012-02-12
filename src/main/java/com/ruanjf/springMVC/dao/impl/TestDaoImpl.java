package com.ruanjf.springMVC.dao.impl;

import org.springframework.stereotype.Repository;

import com.ruanjf.springMVC.dao.TestDao;
import com.ruanjf.springMVC.dao.base.BaseDao;
import com.ruanjf.springMVC.persistent.Test;

@Repository // 用于标注数据访问组件，即DAO组件
public class TestDaoImpl extends BaseDao<Test,String> implements TestDao {
	
}
