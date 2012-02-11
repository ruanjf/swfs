package com.ruanjf.springMVC.dao.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ruanjf.springMVC.dao.hibernate.GenericDao;
import com.ruanjf.springMVC.dao.hibernate.GenericEntityDao;
import com.ruanjf.springMVC.dao.jdbc.SimpleJdbcDao;
import com.ruanjf.springMVC.dao.support.GenericsUtils;
import com.ruanjf.springMVC.dao.support.Page;

/**
 * @author ruanjf
 * @since  2012-01-02
 * 提供dao的所有操作<br>
 * 实现类由spring注入:<br>
 * {@link com.ruanjf.springMVC.dao.hibernate.GenericEntityDao}
 * {@link com.ruanjf.springMVC.dao.hibernate.GenericDao}
 * {@link com.ruanjf.springMVC.dao.jdbc.SimpleJdbcDao}
 */
@Component // 泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
public class BaseDao<T,PK extends Serializable> implements IBaseDao<T,PK>{
	
	protected Class<T> entityClass;// DAO所管理的Entity类型.
	private GenericEntityDao<T,PK> genericEntityDao;
	private GenericDao genericDao;
	private SimpleJdbcDao simpleJdbcDao;
	
	public Class<T> getEntityClass() {return entityClass;}
	public void setEntityClass(Class<T> entityClass) {
		genericEntityDao.setEntityClass(this.entityClass);//手动注入实体类类型
		this.entityClass = entityClass;
	}
	
	public GenericEntityDao<T, PK> getGenericEntityDao() {
		return genericEntityDao;
	}
	@Autowired
	public void setGenericEntityDao(GenericEntityDao<T, PK> genericEntityDao) {
		genericEntityDao.setEntityClass(this.entityClass); //在spring注入泛型实体dao时候注入实体类型
		this.genericEntityDao = genericEntityDao;
	}
	public GenericDao getGenericDao() {
		return genericDao;
	}
	@Autowired
	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}
	public SimpleJdbcDao getSimpleJdbcDao() {
		return simpleJdbcDao;
	}
	@Autowired
	public void setSimpleJdbcDao(SimpleJdbcDao simpleJdbcDao) {
		this.simpleJdbcDao = simpleJdbcDao;
	}
	/**
	 *让spring提供构造函数注入
	 */
	public BaseDao(Class<T> type) {
		this.entityClass = type;//spring构建时,根据配置文件最先注入实体类型
	}
	
	public BaseDao(){
		// 自动注入实体类型
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	public void clear() {
		genericDao.clear();
	}
	
	public Query createQuery(String hql, Object... values) {
		return genericDao.createQuery(hql, values);
	}
	
	
	public void delete(T entityObject) {
		genericEntityDao.delete(entityObject);
	}
	
	
	public void deleteById(PK id) {
		genericEntityDao.deleteById(id);
	}
	
	
	public void evict(T entityObject) {
		genericEntityDao.evict(entityObject);
	}
	
	
	public List<T> find(String hql, Object... values) {
		return genericDao.find(hql, values);
	}
	
	
	public List<T> findByNamedParams(String hql, String[] paramNames,
			Object... values) {
		return genericDao.findByNamedParams(hql, paramNames, values);
	}
	
	
	public void flush() {
		genericDao.flush();
	}
	
	
	public List<T> getAll() {
		return genericEntityDao.getAll();
	}
	
	
	public T getById(PK id) {
		return genericEntityDao.getById(id);
	}
	
	
	public Session getNativeHibernateSession() {
		return genericDao.getNativeHibernateSession();
	}
	
	
	public StatelessSession getNativeStatelessHibernateSession() {
		return genericDao.getNativeStatelessHibernateSession();
	}
	
	
	public HibernateTemplate getHibernateTemplate() {
		return genericDao.getHibernateTemplate();
	}
	
	
	public Iterator<T> iterator(String hql, Object... values) {
		return genericDao.iterator(hql, values);
	}
	
	@Override
	public <V> V queryAnObject(String hql, Object... values) {
		return (V) this.createQuery(hql, values).uniqueResult();
	}
	
	
	public SimpleJdbcDao jdbc() {
		return simpleJdbcDao;
	}
	
	
	public T load(PK id) {
		return genericEntityDao.load(id);
	}
	
	
	public void load(T entityObject, PK id) {
		genericEntityDao.load(entityObject, id);
	}
	
	
	public T merge(T entityObject) {
		return genericEntityDao.merge(entityObject);
	}
	
	
	public SQLQuery nativeSqlQuery(String sql,boolean isBind) {
		return genericDao.nativeSqlQuery(sql,isBind);
	}
	
	
	public Page<T> pagedQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		return genericDao.pagedQuery(hql, pageNo, pageSize, values);
	}
	
	
	public Page<T> pagedQueryByStartNo(String hql, int startNo, int pageSize,
			Object... values) {
		return genericDao.pagedQueryByStartNo(hql, startNo, pageSize, values);
	}
	
	
	public void refresh(T entityObject) {
		genericEntityDao.refresh(entityObject);
	}
	
	
	public void save(T entityObject) {
		genericEntityDao.save(entityObject);
	}

	
	public void changeEntityClass(Class<T> entityClass) {
       genericEntityDao.setEntityClass(entityClass);	
	}
	
}
