package com.wy.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.wy.bean.Collect;


/**
 * 
 * @ClassName: CollectMapper 
 * @Description: 文章收藏
 * @author: charles
 * @date: 2020年4月11日 上午9:03:51
 */
public interface CollectMapper {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加
	 * @param collect
	 * @return
	 * @return: int
	 */
	int insert(Collect collect);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询
	 * @param collect
	 * @return
	 * @return: List<Collect>
	 */
	List<Collect> selects(Collect collect);
	/**
	 * 
	 * @Title: selectCount 
	 * @Description: 查询注册用户是否收藏text的文章
	 * @param text
	 * @param userId
	 * @return
	 * @return: int  1:已收藏    0:未收藏
	 */
	int selectCount(@Param("text") String text,@Param("userId")Integer userId);
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 根据ID删除收藏
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteCollect(Integer id);

}
