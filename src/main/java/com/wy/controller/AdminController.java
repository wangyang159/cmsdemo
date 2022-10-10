package com.wy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wy.bean.Article;
import com.wy.bean.User;
import com.wy.service.ArticleService;
import com.wy.service.UserService;

/**
 * 
 * @ClassName: Adminontroller
 * @Description: 管理员中心
 * @author: charles
 * @date: 2020年4月3日 上午10:58:11
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: index
	 * @Description: 管理员的首页
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index.do")
	public String index() {

		return "admin/index";

	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: 文章列表
	 * @param model
	 * @param article
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("/articles.do")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "6")Integer pageSize) {
		//管理员不需要传id但是默认要显示待审核的文章
		if(article.getStatus()==null){
			article.setStatus(0);
		}
		List<Article> selectArticle = articleService.selectArticle(article, pageNum, pageSize);
		PageInfo<Article> info = new PageInfo<>(selectArticle);
		model.addAttribute("info",info);
		return "admin/articles";
		
	}
	
	/**
	 * 
	 * @Title: article 
	 * @Description: 单个文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("/article.do")
	public Article article(Integer id) {
		return articleService.select(id);
	}

	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("/update.do")
	public boolean update(Article article) {
		return articleService.update(article) >0;
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改用户
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("user/update.do")
	public boolean update(User user) {
		return userService.update(user) >0;
	}
	
	/**
	 * 
	 * @Title: users 
	 * @Description: 用户列表
	 * @param model
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("/users.do")
	public String users(Model model,User user,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "6")Integer pageSize) {
		//默认查询没有禁用的用户
		if(user.getLocked()==null) {
			user.setLocked(0);
		}
		
		PageInfo<User> info = userService.selects(user, page, pageSize);
		
		model.addAttribute("info", info);
		model.addAttribute("user", user);
		
		return "admin/users";
	}
	
	/**
	 * 到达军事查询页面
	 */
	@RequestMapping("section.do")
	public String section(){
		return "admin/section";
	}
	
	/**
	 * 查询军事文
	 * @return
	 */
	@RequestMapping("sections.do")
	public String sections(Article article ,@RequestParam(defaultValue="1")Integer pageNum,Model model){
		List<Article> list=articleService.getSections(article,pageNum);
		PageInfo info=new PageInfo<>(list);
		model.addAttribute("info", info);
		model.addAttribute("article", article);
		return "admin/sections";
	}
	
	/**
	 * 去添加
	 * @return
	 */
	@RequestMapping("add.do")
	public String add(){
		return "admin/add";
	}
	
	/**
	 * 添加处理
	 * @return
	 */
	@RequestMapping("toAdd.do")
	public String toAdd(String title,String content){
		articleService.add(title,content);
		return "redirect:/admin/index.do";
	}
}
