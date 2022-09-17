package com.wy.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @ClassName: Md5Util 
 * @Description: 对密码进行加密处理
 * @author: charles
 * @date: 2020年3月12日 上午9:51:41
 */
public class Md5Util {
	
	/**
	 * 直接对密码进行散列，那么黑客可以对通过获得这个密码散列值，
	 * 然后通过查散列值字典（例如MD5密码破解网站），得到某用户的密码。
	 * 加Salt可以一定程度上解决这个问题
	 */
	private static String salt ="qazwsx1234";
	
	
	public static String encode(String password) {
       return DigestUtils.md5Hex(password + salt);
	}

}
