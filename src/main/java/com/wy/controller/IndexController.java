package com.wy.controller;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.wy.bean.*;
import com.wy.service.*;
import com.wy.utils.HLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

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

	@Resource
	private CollectService collectService;//收藏

	@Resource
	private CommentService commentService;//评论

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

//	@Autowired
//	private KafkaTemplate kafkaTemplate;


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

		//使用线程
		Thread t1;
		Thread t2;
		Thread t3;
		Thread t4;
		//查询所有的栏目,该线程为必须品
		t1=new Thread(new Runnable() {
			@Override
			public void run() {
				//查询所有的栏目:使用redis优化
				List<Channel> channels = (List<Channel>) redisTemplate.opsForValue().get("channels");
				if(channels==null){
					//如果redis数据库中没有那么在重mysql中查询并放入redis
					channels = channelService.selects();
					redisTemplate.opsForValue().set("channels", channels);

					//你也可以这样写  意为五分钟后数据失效
					//redisTemplate.opsForValue().set("channels", channels ,5 , TimeUnit.MINUTES);
				}
				model.addAttribute("channels", channels);
			}
		});

		// 判断栏目ID 不为空 也就是说当前不是查询热点那么就要查询其下分类
		t2=new Thread(new Runnable() {

			@Override
			public void run() {
				if(article.getChannelId()!=null){
					List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
					model.addAttribute("categorys", categorys);
				}else{
					//如果栏目id是空的那么就代表这查询的是热点，并为为热点查询广告
					List<Slide> slides = slideService.getAll();
					model.addAttribute("slides", slides);
					//限制查询热点文章
					article.setHot(1);
				}
			}
		});

		//前两个线程决定查什么文章，第三个线程正式查文章
		t3=new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//无论是什么情况控制查询文章不是被逻辑删除的
				article.setDeleted(0);
				//不能查询非审核之后的文章
				article.setStatus(1);
				//查询符合条件的所有文章
				List<Article> selectArticle = articleService.selectArticle(article, pageNum, 6);
				PageInfo info=new PageInfo<>(selectArticle);
				model.addAttribute("info", info);
			}

		});

		//为首页查询五条最新的文章
		t4=new Thread(new Runnable() {

			@Override
			public void run() {
				//	封装该查询条件
				List<Article> newArticles=(List<Article>) redisTemplate.opsForValue().get("newArticles");
				if(newArticles==null){
					Article latest = new Article();
					latest.setDeleted(0);
					//不能查询非审核之后的文章
					latest.setStatus(1);
					//如果redis数据库中没有那么在重mysql中查询并放入redis
					newArticles = articleService.selectArticle(latest, 1, 5);
					redisTemplate.opsForValue().set("newArticles", newArticles);
				}
				PageInfo lastArticles=new PageInfo<>(newArticles);
				model.addAttribute("lastArticles", lastArticles);
			}
		});

		//启动线程并保证线程顺序
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
	public String detail(Model model, Integer id, HttpSession session, @RequestParam(defaultValue="1")Integer page) {
		//查询文章
		Article article = articleService.select(id);
		model.addAttribute("article", article);

		//查询文章是否被当前用户收藏
		// 前提：如果用户已经登录则查询是否收藏
		User user=(User) session.getAttribute("user");
		if (null != user) {
			int isCollect = collectService.selectCount(article.getTitle(), user.getId());
			model.addAttribute("isCollect", isCollect);
		}

		//查询评论
		PageInfo<Comment> info = commentService.selects(id, page, 5);
		model.addAttribute("info", info);

		//发出信息 ：文章id,1
//		kafkaTemplate.send("wy",id+","+1);

		return "index/article";
	}

	@RequestMapping("es.do")
	public String es(Model model,String key,@RequestParam(defaultValue="1")Integer pageNum){
		//高亮查询
		PageInfo<?> info = HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, 5, new String[]{"title"}, "id", key);
		model.addAttribute("info", info);
		model.addAttribute("key", key);
		return "index/index";
	}

}
