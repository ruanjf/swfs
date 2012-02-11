package com.ruanjf.springMVC.commons;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester3.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class Constants {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);
	
	private static Constants constants = new Constants();
	
	private Constants(){}
	
	public static Constants getInstance(){
		if(constants==null)
			constants = new Constants();
		return constants;
	}
	
//	private static final String APP_INFO_PATH = this.getClass().getResource("/")+"classpath*:/appInfo.xml";
	
	private static AppInfo APP_INFO;
	
	private static  Map<String, String> areaMap;
	
	/**
	 * 登录用户的key
	 */
	public static String USER_LOGIN_KEY="userId";
	
	/**
	 * 系统信息
	 * @return
	 */
	public AppInfo getAppInfo(){
		if(APP_INFO==null){
			Digester disDigester = new Digester();
			disDigester.setValidating(false);
			disDigester.addObjectCreate("info", AppInfo.class);
			disDigester.addCallMethod("info/slogan", "setSlogan", 0);
			disDigester.addCallMethod("info/logo", "setLogo", 0);
			disDigester.addCallMethod("info/incUrl", "setIncUrl", 0);
			disDigester.addCallMethod("info/areaData", "setAreaData", 0);
			disDigester.addCallMethod("info/success", "setSuccess", 0);
			disDigester.addCallMethod("info/failure", "setFailure", 0);
			disDigester.addCallMethod("info/copyright", "setCopyright", 0);
			
			try {
				System.out.println("");
				System.out.println("url:"+this.getClass().getResource("/config/"));
				System.out.println("");
				APP_INFO = disDigester.parse(this.getClass().getResource("/")+"../appInfo.xml");
			} catch (IOException e) {
				if(LOGGER.isDebugEnabled())
					e.printStackTrace();
				LOGGER.error("文件地址有误！", e);
			} catch (SAXException e) {
				if(LOGGER.isDebugEnabled())
					e.printStackTrace();
				LOGGER.error("xml格式有错", e);
			}
		}
		return APP_INFO;
	}
	
	public Map<String, String> getAreaDataMap(){
		if(areaMap==null || areaMap.isEmpty()){
			String areaData = Constants.getInstance().getAppInfo().getAreaData();
			String[] areas = areaData.split(",");
			if(areas!=null && areas.length>0){
				areaMap = new HashMap<String, String>(areas.length);
				for (String area : areas) {
					String[] kv = area.split("\\|");
					if(kv!=null && kv.length==2)
						areaMap.put(kv[0], kv[1]);
				}
				return areaMap;
			}
		}
		return areaMap;
	}
	
	public static void main(String[] args) {
		Constants.getInstance().getAppInfo();
	}

}
