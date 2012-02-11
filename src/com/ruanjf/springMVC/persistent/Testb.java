package com.ruanjf.springMVC.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="testb")
public class Testb {
	
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") // 使用uuid的生成策略
	@GeneratedValue(generator="idGenerator") // 要设置特定的生成器否则无法生成表
	@Column(length=32,nullable=false)
	private String id;
	
	@Column(length=2000)
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
