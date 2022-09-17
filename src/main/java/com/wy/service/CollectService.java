package com.wy.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.wy.bean.Collect;

public interface CollectService {

	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 根据ID删除收藏
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteCollect(Integer id);
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加
	 * @param collect
	 * @return
	 * @return: int
	 */
	boolean insert(Collect collect);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询
	 * @param collect
	 * @return
	 * @return: List<Collect>
	 */
	PageInfo<Collect> selects(Collect collect,Integer page,Integer pageSize);
	
	
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
}
