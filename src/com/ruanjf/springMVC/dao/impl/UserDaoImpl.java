package com.ruanjf.springMVC.dao.impl;

import org.springframework.stereotype.Repository;

import com.ruanjf.springMVC.dao.UserDao;
import com.ruanjf.springMVC.dao.base.BaseDao;
import com.ruanjf.springMVC.persistent.User;

@Repository // 用于标注数据访问组件，即DAO组件
public class UserDaoImpl extends BaseDao<User, String> implements UserDao {
	
}
