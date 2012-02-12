package com.ruanjf.springMVC.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.dao.UserDao;
import com.ruanjf.springMVC.dao.support.Page;
import com.ruanjf.springMVC.persistent.User;
import com.ruanjf.springMVC.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	@Override
	public User login(String username,String password){
		List<User> list = userDao.find("from User u where u.userId=? and u.password=?", Long.valueOf(username),password);
		if(Utils.notEmpty(list))
			return list.size()==1?list.get(0):null;
		return null;
	}

	@Override
	public User getUserById(String userId) {
		if(Utils.isEmpty(userId))
			return null;
		List<User> list = userDao.find("from User u where u.userId=?", Long.valueOf(userId));
		if(Utils.notEmpty(list))
			return list.size()==1?list.get(0):null;
		return null;
	}
	

	@Override
	public Page<User> getList(int pageNo, int pageSize, Long userId) {
		String hql = "from User u";
		if(userId != null && userId>0)
			hql +=" u where u.userId="+userId;
		hql+=" order by u.userId";
		
		if(pageNo==0){
			return new Page<User>(0, 0, 0, userDao.find(hql));
		}
		return userDao.pagedQuery(hql, pageNo, pageSize);
	}
	
	@Override
	public long getNextId(){
		Object id = userDao.queryAnObject("select max(u.userId) from User u");
		if(id==null)
			return 10000L;
		return Long.valueOf(id.toString())+1;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public boolean delUser(User user) {
		if(user!=null){
			userDao.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserById(String id) {
		if(Utils.notEmpty(id)){
			userDao.deleteById(id);
			return true;
		}
		return false;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
