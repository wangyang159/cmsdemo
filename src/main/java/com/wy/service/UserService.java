package com.wy.service;


import com.github.pagehelper.PageInfo;
import com.wy.bean.User;

public interface UserService {

	/**
	 * 
	 * @Title: selects
	 * @Description: TODO
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: Pageinfo<User>
	 */
	PageInfo<User> selects(User user, Integer page, Integer pageSize);

	/**
	 * 
	 * @Title: update
	 * @Description: 管理员修改用户修改用户
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
	boolean  insert(User user); 
	
	/**
	 * 
	 * @Title: selectByName 
	 * @Description: 根据用户名查询用户
	 * @param name
	 * @return
	 * @return: User
	 */
	User selectByName(String name);
	/**
	 * 
	 * @Title: login 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);
	
	
}
