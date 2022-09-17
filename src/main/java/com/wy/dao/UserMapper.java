package com.wy.dao;

import java.util.List;

import com.wy.bean.User;


/**
 * 
 * @ClassName: UserMapper
 * @Description: 用户信息
 * @author: charles
 * @date: 2020年4月7日 上午9:38:31
 */
public interface UserMapper {
	/**
	 * 
	 * @Title: selects
	 * @Description: 用户列表
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(User user);

	/**
	 * 
	 * @Title: update
	 * @Description: 修改用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);

	/**
	 * 
	 * @Title: insert 
	 * @Description:注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int  insert(User user); 
	
	/**
	 * 
	 * @Title: selectByName 
	 * @Description: 根据用户名查询用户
	 * @param name
	 * @return
	 * @return: User
	 */
	User selectByName(String name);
	
	
}
