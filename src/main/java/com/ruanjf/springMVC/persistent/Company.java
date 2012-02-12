package com.ruanjf.springMVC.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="company")
public class Company{
	
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
	private Long modifyUser;
	
	@Column(length=10)
	private Long companyId;
	
	/**
	 * 公司名称
	 */
	@Column(length=200)
	private String name;
	
	/**
	 * 存放省市的id
	 */
	@Column(length=2000)
	private String area;
	/**
	 * 公司地址的详细地址
	 */
	@Column(length=2000)
	private String address;
	
	/**
	 * 用于搜索的地址
	 */
	@Column(length=200)
	private String searchAddress;
	
	/**
	 * 联系人
	 */
	@Column(length=200)
	private String contactPerson;
	
	/**
	 * 联系人手机
	 */
	@Column(length=200)
	private String contactPhone;
	
	/**
	 * 联系人座机
	 */
	@Column(length=200)
	private String contactTelephone;
	
	/**
	 * 负责该公司的人员（userId）
	 */
	@Column(length=200)
	private Long principal;
	
	/**
	 * 备注
	 */
	@Column(length=2000,name="description")
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	public Long getPrincipal() {
		return principal;
	}

	public void setPrincipal(Long principal) {
		this.principal = principal;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public void setModifyUser(Long modifyUser) {
		this.modifyUser = modifyUser;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSearchAddress() {
		return searchAddress;
	}

	public void setSearchAddress(String searchAddress) {
		this.searchAddress = searchAddress;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
