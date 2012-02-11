package com.ruanjf.springMVC.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ruanjf	
 * @since  2012-01-03
 * 所有action的基类提供日记能力和通用功能
 */
public abstract class BaseController{

	//让子类拥有日志记录的能力
	protected  Logger log=LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 提供获取request,response,session的能力;向客户端返还多种数据格式的辅助方法
	 */
//	protected static Struts2Util s2Util;

}
