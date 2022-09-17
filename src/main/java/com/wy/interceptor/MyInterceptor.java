package com.wy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: MyInterceptor 
 * @Description: 个人中心拦截器
 * @author: charles
 * @date: 2020年4月10日 上午9:56:23
 */
public class MyInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	     //不拦截规则--- 如果用户已经登录则不拦截 false:如果有session 则返回session.如果没有则返回null
		HttpSession session = request.getSession(false);
		if(session!=null) {//如果存在session 
			Object user = session.getAttribute("user");//则从session获取登录的user对象
			if(user!=null)//如果对象不为空
			 return true;//放行
		}
		//没有登录，跳转到登录页面
		request.setAttribute("msg", "请登录后再试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		
		return false;
	}

}
