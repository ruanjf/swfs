package com.ruanjf.springMVC.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
public class User{
	
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") // 使用uuid的生成策略
	@GeneratedValue(generator="idGenerator") // 要设置特定的生成器否则无法生成表
	@Column(length=32,nullable=false)
	private String id;
	
	/**
	 * 创建时间
	 */
	@Column
	private Date createTime;
	
	/**
	 * 创建用户
	 */
	@Column(length=10)
	private Long createUser;
	
	/**
	 * 修改时间
	 */
	@Column
	private Date modifyTime;
	
	/**
	 * 修改用户
	 */
	@Column(length=10)
	public Long modifyUser;
	
	public static enum Status{
		/**
		 * 用户可用 
		 */
		USE,
		/**
		 * 用户不可用
		 */
		DISUSE;

	}

	/**
	 * 用于登录，不可以重复
	 */
	@Column(length=10)
	private Long userId;
	
	/**
	 * 用户名
	 */
	@Column(length=200)
	private String username;
	
	/**
	 * 密码
	 */
	@Column(length=200)
	private String password;
	
	/**
	 * 用户状态
	 */
	@Column(length=200)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * 用户的角色
	 */
	@Column(length=200)
	private String roles;
	
	/**
	 * 个人描述
	 */
	@Column(length=2000,name="description")
	private String desc;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Long modifyUser) {
		this.modifyUser = modifyUser;
	}

}
