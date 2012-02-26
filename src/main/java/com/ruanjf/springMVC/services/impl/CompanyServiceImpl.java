package com.ruanjf.springMVC.services.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruanjf.springMVC.commons.Utils;
import com.ruanjf.springMVC.dao.CompanyDao;
import com.ruanjf.springMVC.dao.support.Page;
import com.ruanjf.springMVC.persistent.Company;
import com.ruanjf.springMVC.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Resource
	private CompanyDao companyDao;

	@Override
	public Company getCompany(String companyId) {
		if(Utils.isEmpty(companyId))
			return null;
		List<Company> list = companyDao.find("from Company c where c.companyId=?", Long.valueOf(companyId));
		if(Utils.notEmpty(list))
			return list.size()==1?list.get(0):null;
		return null;
	}

	@Override
	public Page<Company> getList(int pageNo, int pageSize, Long userId) {
		String hql = "from Company c";
		if(userId != null && userId>0)
			hql +=" where c.principal="+userId;
		hql+=" order by c.companyId";
		
		if(pageNo==0){
			return new Page<Company>(0, 0, 0, companyDao.find(hql));
		}
		return companyDao.pagedQuery(hql, pageNo, pageSize);
	}
	
	@Override
	public List<Company> getSearchList(Long userId, String key) {
		
		StringBuffer hql = new StringBuffer("from Company");
		if(Utils.isNumeric(key) && key.length() > 6){
			hql.append(" c where c.contactPhone='").append(key) // 联系人手机
				.append("' or contactTelephone like '%").append(key).append("'"); // 联系人座机
		}else{
			hql.append(" c where c.searchAddress like '%,").append(key).append(",%'"); // 关键字
			hql.append(" or c.name='").append(key).append("'"); // 公司名称
			hql.append(" or c.contactPerson='").append(key).append("'"); // 公司联系人
			hql.append(" or c.address like '%").append(key).append("%'"); // 公司地址
		}
		
//		if(userId != null && userId>0)
//			hql.append(" and c.principal !=").append(userId);
		return companyDao.find(hql.toString());
	}

	@Override
	public void saveCompany(Company company) {
		if(company==null)
			return;
		if(company.getCompanyId()==null)
			company.setCompanyId(getNextId());
		companyDao.save(company);
	}

	@Override
	public boolean delCompany(Company company) {
		if(company!=null){
			companyDao.delete(company);
			return true;
		}
		return false;
	}

	@Override
	public boolean delCompanyById(String id) {
		if(Utils.notEmpty(id)){
			companyDao.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public long getNextId(){
		Object id = companyDao.queryAnObject("select max(c.companyId) from Company c");
		if(id==null)
			return 10000L;
		return Long.valueOf(id.toString())+1;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

}
