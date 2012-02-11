package com.ruanjf.springMVC.commons;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.ruanjf.springMVC.persistent.User;

public class Utils {
	
	public static boolean isEmpty(String str){
		return str==null?true:(str.trim().length()>0?false:true);
	}
	
	public static boolean notEmpty(String str){
		return str==null?false:(str.trim().length()>0?true:false);
	}
	
	public static boolean isEmpty(Collection l){
		return l==null?true:l.isEmpty();
	}
	
	public static boolean notEmpty(Collection l){
		return l==null?false:!l.isEmpty();
	}
	
	public static User getLoginUser(HttpServletRequest request){
		if(request==null)
			return null;
		return (User)request.getSession().getAttribute(Constants.USER_LOGIN_KEY);
	}
	
	public static void setLoginUser(HttpServletRequest request,User user){
		if(request==null)
			return;
		request.getSession().setAttribute(Constants.USER_LOGIN_KEY,user);
	}
	
	public static void removeLoginUser(HttpServletRequest request){
		request.getSession().setAttribute(Constants.USER_LOGIN_KEY, null);
	}
	
	public static int getInt(HttpServletRequest request, String name){
		String n = request.getParameter(name);
		if(isEmpty(n))
			return 0;
		return NumberUtils.parseNumber(n, Integer.class);
	}
	
	public static Long getLong(HttpServletRequest request, String name){
		String n = request.getParameter(name);
		if(isEmpty(n))
			return 0L;
		return NumberUtils.parseNumber(n, Long.class);
	}
	
	public static boolean isNumeric(String str){
		if(isEmpty(str))
			return false;
		for (byte b : str.getBytes()) {
			if(!Character.isDigit(b))
				return false;
		}
		return true;
	}
	
	public static boolean isInteger(String str){
	    if (isEmpty(str)) {
	      return false;
	    }
	    if ((str.startsWith("-")) && (str.length() > 1)) {
	      str = str.substring(1);
	    }
	    return isNumeric(str);
	}
	
	public static String getParameter(HttpServletRequest request, String name){
		String n = request.getParameter(name);
		if(notEmpty(n)){
			return n;
//			try {
//				System.out.println(new String(n.getBytes("ISO8859_1"),"gb2312"));
//				System.out.println(new String(n.getBytes("gbk"),"UTF-8"));
//				return new String(n.getBytes("gbk"),"UTF-8");
//				return URLDecoder.decode(n, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLDecoder.decode("鍒嗕负", "gbk"));
		int a= '2';
		int z='A';
		System.out.println(a);
		System.out.println(z);
	}
	
}
