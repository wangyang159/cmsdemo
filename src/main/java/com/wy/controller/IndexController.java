package com.wy.controller;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.wy.bean.Article;
import com.wy.bean.Category;
import com.wy.bean.Channel;
import com.wy.bean.Slide;
import com.wy.service.ArticleService;
import com.wy.service.ChannelService;
import com.wy.service.SlideService;

/**
 * 
 * @ClassName: IndexController 
 * @Description: cms首页控制器
 * @author: charles
 * @date: 2020年4月7日 上午10:39:01
 */
@Controller
public class IndexController {
	
	@Resource
	private  ChannelService channelService;

	@Resource
	private ArticleService articleService;

	@Resource
	private SlideService slideService;

	/**
	 *
	 * @Title: index
	 * @Description: 进入首页
	 * @return
	 * @return: String
	 */
	@RequestMapping("index.do")
	public String index(Model model,Article article,@RequestParam(defaultValue="1")Integer pageNum) {
		//封装查询条件
		model.addAttribute("article", article);
		//查询所有的栏目
		List<Channel> channels = channelService.selects();
		model.addAttribute("channels", channels);
		// 栏目ID 不为空 也就是说当前页面焦点不是热点，那么就要查询其下分类
		if(article.getChannelId()!=null){
			List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
			model.addAttribute("categorys", categorys);
		}else{
			//如果栏目id是空的那么就代表这查询的是热点，为热点查询广告
			List<Slide> slides = slideService.getAll();
			model.addAttribute("slides", slides);
			//当没有栏目id时限制只出现热点文章
			article.setHot(1);
		}
		//无论是什么情况控制查询文章不是被逻辑删除的
		article.setDeleted(0);
		//不能查询非审核之后的文章
		article.setStatus(1);
		//查询符合条件的所有文章
		List<Article> selectArticle = articleService.selectArticle(article, pageNum, 6);
		PageInfo info=new PageInfo<>(selectArticle);
		model.addAttribute("info", info);
		//查询五条最新的文章
		Article latest = new Article();
		latest.setDeleted(0);
		//不能查询非审核之后的文章
		latest.setStatus(1);
		List<Article> newArticles = articleService.selectArticle(latest, 1, 5);
		PageInfo lastArticles=new PageInfo<>(newArticles);
		model.addAttribute("lastArticles", lastArticles);
		return "index/index";
	}

	/**
	 *
	 * @Title: detail
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("detail.do")
	public String detail(Model model,Integer id) {
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		return "index/article";
	}
}
