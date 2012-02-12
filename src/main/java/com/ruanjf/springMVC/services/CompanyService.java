package com.ruanjf.springMVC.services;


import java.util.List;

import com.ruanjf.springMVC.dao.support.Page;
import com.ruanjf.springMVC.persistent.Company;

public interface CompanyService {
	
	public abstract Company getCompany(String companyId);
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param userId 没有指定用户时，获取全部用户的
	 * @return
	 */
	public abstract Page<Company> getList(int pageNo, int pageSize, Long userId);
	
	public abstract void saveCompany(Company company);
	
	public abstract boolean delCompany(Company company);
	
	/**
	 * 现阶段不建议使用
	 * @param id
	 * @return
	 */
	public abstract boolean delCompanyById(String id);

	public abstract long getNextId();

	/**
	 * 获得不属于指定用户的客户列表
	 * @param userId
	 * @param key 包含特定地址的用户
	 * @return
	 */
	public abstract List<Company> getSearchList(Long userId, String key);

}
