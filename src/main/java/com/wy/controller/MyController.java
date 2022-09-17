package com.wy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.wy.bean.Collect;
import com.wy.bean.Comment;
import com.wy.service.CollectService;
import com.wy.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wy.bean.Article;
import com.wy.bean.User;
import com.wy.service.ArticleService;

@Controller
@RequestMapping("/my")
public class MyController {
	
	@Resource
	private ArticleService articleService;

	@Resource
	private CollectService collectService;//收藏

	@Resource
	private CommentService commentService;//评论

	/**
	 * 跳转到个人页面
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(){
		
		return "my/index";
	}
	
	/**
	 * 跳转我的文章
	 * @return
	 */
	@RequestMapping("/articles.do")
	public String articles(Article article,@RequestParam(defaultValue="1")Integer pageNum,
			Model model,HttpSession session){
		User user=(User) session.getAttribute("user");
		article.setUserId(user.getId());
		//文章查询限制
		article.setDeleted(0);
		List<Article> selectArticle = articleService.selectArticle(article, pageNum, 3);
		PageInfo info=new PageInfo<>(selectArticle);
		model.addAttribute("info", info);
		return "my/articles";
	}
	
	/**
	 * 跳转发布文章
	 * @return
	 */
	@RequestMapping("/push.do")
	public String push(){
		return "my/fwb";
	}
	
	//上传地址
	//@Value(value="${pushPath}") 正常情况下应该有个properties的配置 但估计是SSM的问题获取不到这个值了，这里就先写死了
//	private String pushPath = "D:/pic";
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 执行发布
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("/publish.do")
	public boolean publish(MultipartFile file,  Article article ,HttpSession session) {
		//判断是否上传了文件
		if(file!=null &&!file.isEmpty()) {
		 //文件上传	
		 String path = "D:/pic";//文件上传的路径
		 //获取原始名称 a.jpg
		 String oldFileName = file.getOriginalFilename();
		 // 为了防止文件重名.使用uuid产生随机数
		  String fileName =UUID.randomUUID() +oldFileName.substring(oldFileName.lastIndexOf("."));
			File f = new File(path,fileName);
		  try {
			file.transferTo(f);//把文件写入硬盘
			article.setPicture(fileName);//数据库存储文件的名称
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		User user=(User) session.getAttribute("user");
		article.setUserId(user.getId());  //文章作者
		//以下信息也可以放在配置文件中为了方便先写死
		article.setStatus(0);//待审
		article.setCreated(new Date());//发布时间
		article.setHits(0);//点击量
		article.setHot(0);//非热门
		article.setDeleted(0);//未删除
		article.setContentType("0");//普通html文本
		
		return articleService.insertArticle(article) >0;
	}
	
	//文章详情
	@ResponseBody
	@GetMapping("/article.do")
	public Article article(Integer id) {
		return articleService.select(id);
	}

	/**
	 *
	 * @Title: collect
	 * @Description: 收藏文章
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("collect.do")
	public boolean collect(Collect collect, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {// 如果已经登录则执行收藏
			collect.setUserId(user.getId());// 收藏人
			collect.setCreated(new Date());// 收藏时间
			return collectService.insert(collect);
		}
		return false;// 没有登录则不执行收藏
	}

	/**
	 *
	 * @Title: collect
	 * @Description: 评论文章
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("addComment.do")
	public boolean addComment(Comment comment, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {// 如果已经登录则才能评论
			comment.setUserId(user.getId());// 评论人
			comment.setCreated(new Date());// 收藏时间
			return commentService.insert(comment) >0;
		}
		return false;// 没有登录则不能评论

	}

}
