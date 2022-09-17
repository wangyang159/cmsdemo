package com.wy.service;

import java.util.List;

import com.wy.bean.Article;

public interface ArticleService {
	/**
	 * 添加文章 方法
	 * @param article
	 * @return
	 */
	Integer insertArticle(Article article);
	
	/**
	 * 查询方法
	 * @param article 先使用文章对象，后期如有需要在更改
	 * @return
	 */
	List<Article> selectArticle(Article article,Integer pageNum,Integer pageSize);

	/**
	 * 查询单个的文章
	 * @param id
	 * @return
	 */
	Article select(Integer id);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int update(Article article);

	/**
	 * 查询军事文章
	 * @param article
	 * @return
	 */
	List<Article> getSections(Article article,Integer pageNum);

	/**
	 * 添加
	 * 
	 */
	void add(String title,String content); 
}
