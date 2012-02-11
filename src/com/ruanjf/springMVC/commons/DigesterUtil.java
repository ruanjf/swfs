package com.ruanjf.springMVC.commons;

import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;


public class DigesterUtil {
	
	private static DigesterUtil digesterUtil = new DigesterUtil();
	
	private DigesterUtil(){
	}
	
	public static DigesterUtil getInstance(){
		if(digesterUtil==null)
			digesterUtil = new DigesterUtil();
		return digesterUtil;
	}
	
	/**
	 * 没有缓存xml文件
	 * @return
	 */
	public Digester getDigester(String url){
		System.out.println();
		System.out.println(url);
		System.out.println();
		Digester digester = new Digester();
		try {
			digester.parse(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return digester;
		
	}

}
