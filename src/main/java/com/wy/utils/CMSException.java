package com.wy.utils;
/**
 * 
 * @ClassName: CMSException 
 * @Description: cms的自定义异常
 * @author: charles
 * @date: 2020年4月9日 上午10:59:06
 */
public class CMSException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public String message;//消息

	public CMSException() {

	}

	public CMSException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
