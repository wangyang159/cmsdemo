package com.wy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wy.bean.Comment;

public interface CommentService {

	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据文章id 查询评论
	 * @param articleId
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Comment> selects(Integer articleId,Integer page,Integer pageSize);
}
