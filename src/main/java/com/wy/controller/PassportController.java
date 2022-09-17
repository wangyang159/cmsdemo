package com.wy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.bean.User;
import com.wy.service.UserService;
import com.wy.utils.CMSException;
import com.wy.utils.CMSResult;


@RequestMapping("passport")
@Controller
public class PassportController {
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 跳转注册页面
	 * @return
	 * @return: String
	 */
	@GetMapping("reg.do")
	public String reg() {
		return "passport/reg";
	}

	/**
	 * 
	 * @Title: reg 
	 * @Description: 普通用户执行注册
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("reg.do")
	public CMSResult<User> reg(Model model,User user) {
		CMSResult<User> result =new CMSResult<User>();
		try {
			userService.insert(user);
			result.setCode(200);//状态码
			result.setMsg("恭喜注册成功,请登录");//错误消息
		} catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//状态码
			result.setMsg(e.message);//错误消息
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//状态码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}

	/**
	 * 
	 * @Title: login 
	 * @Description: 普通用户去登录
	 * @return
	 * @return: String
	 */
	@GetMapping("login.do")
	public String login() {
		return "passport/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 普通用户执行登录
	 * @param user
	 * @return
	 * @return: CMSResult<User>
	 */
	@ResponseBody
	@PostMapping("login.do")
	public CMSResult<User> login(User user,HttpSession session){
		CMSResult<User> result =new CMSResult<User>();
		try {
			User u = userService.login(user);
			result.setCode(200);
			result.setMsg("登录成功");
			//登录成功 存session
			session.setAttribute("user", u);
		} catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//状态码
			result.setMsg(e.message);//错误消息
			
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//状态码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	
		
		
	}
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: 注销
	 * @param session
	 * @return
	 * @return: String
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.do";
	}

	/**
	 * 检查用户名是否已存在
	 * @param username
	 * @return
	 */
	@RequestMapping("checkpass.do")
	@ResponseBody
	public boolean checkpass(String username){
		User user = userService.selectByName(username);
		return user==null;
	}

	/**
	 * 
	 * @Title: loginAdmin 
	 * @Description: 跳转到管理员的登录页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("admin/toLogin.do")
	public String loginAdmin() {
		return  "passport/login_admin";
		
	}
	
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 管理用户执行登录
	 * @param user
	 * @return
	 * @return: CMSResult<User>
	 */
	@ResponseBody
	@PostMapping("admin/login.do")
	public CMSResult<User> loginAdmin(User user,HttpSession session){
		CMSResult<User> result =new CMSResult<User>();
		try {
			User u = userService.login(user);
			result.setCode(200);
			result.setMsg("登录成功");
			//如果是普通用户
			if(u.getRole().equals("0")) {//role：1:管理员  0：普通用户
				throw new CMSException("没有登录权限");
			}
			session.setAttribute("admin", u);
		} catch (CMSException e) {//捕获自定义异常
			e.printStackTrace();
			result.setCode(300);//状态码
			result.setMsg(e.message);//错误消息
			
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(400);//状态码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}
}
