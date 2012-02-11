package com.ruanjf.springMVC.dao.impl;

import org.springframework.stereotype.Repository;

import com.ruanjf.springMVC.dao.CompanyDao;
import com.ruanjf.springMVC.dao.base.BaseDao;
import com.ruanjf.springMVC.persistent.Company;

@Repository // 用于标注数据访问组件，即DAO组件
public class CompanyDaoImpl extends BaseDao<Company, String> implements CompanyDao {

}
