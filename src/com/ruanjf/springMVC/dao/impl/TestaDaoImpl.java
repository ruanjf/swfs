package com.ruanjf.springMVC.dao.impl;

import org.springframework.stereotype.Repository;

import com.ruanjf.springMVC.dao.TestaDao;
import com.ruanjf.springMVC.dao.base.BaseDao;
import com.ruanjf.springMVC.persistent.Testa;

@Repository // 用于标注数据访问组件，即DAO组件
public class TestaDaoImpl extends BaseDao<Testa,String> implements TestaDao {
	
}
