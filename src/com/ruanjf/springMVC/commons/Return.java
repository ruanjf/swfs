package com.ruanjf.springMVC.commons;

/**
 * 与前端信息交互
 * @author gg
 *
 */
public class Return {
	
	private int code;
	
	private String msg;
	
	/**
	 * 与前端信息交互
	 * @param code 0 表示正常 >0 表示有问题
	 * @param msg 需要提示的信息
	 */
	public Return(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 0 表示正常 >0 表示有问题
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
