package com.wy.dao;

import java.util.List;

import com.wy.bean.Comment;


/**
 * 
 * @ClassName: CommentMapper 
 * @Description: 评论
 * @author: charles
 * @date: 2020年4月11日 上午11:25:04
 */
public interface CommentMapper {
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
	List<Comment> selects(Integer articleId);
	
	/**
	 * 
	 * @Title: updateAritlce 
	 * @Description: 增加文章的评论数量
	 * @param id
	 * @return
	 * @return: int
	 */
	int updateAritlce(Integer id);

}
