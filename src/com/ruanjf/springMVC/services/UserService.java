package com.ruanjf.springMVC.services;


import com.ruanjf.springMVC.dao.support.Page;
import com.ruanjf.springMVC.persistent.User;

public interface UserService {
	
	public abstract User getUserById(String userId);
	
	public abstract void saveUser(User user);
	
	public abstract boolean delUser(User user);
	
	/**
	 * 现阶段不建议使用
	 * @param id
	 * @return
	 */
	public abstract boolean delUserById(String id);
	
	/**
	 * 页数等于零的时候取全部
	 * @param pageNo
	 * @param pageSize
	 * @param userId TODO
	 * @return
	 */
	public abstract Page<User> getList(int pageNo, int pageSize, Long userId);

	/**
	 * @param username
	 * @param password
	 * @return 登录不成功返回 null
	 */
	public abstract User login(String username, String password);

	public abstract long getNextId();

}
