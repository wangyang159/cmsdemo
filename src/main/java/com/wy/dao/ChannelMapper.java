package com.wy.dao;

import java.util.List;

import com.wy.bean.Category;
import com.wy.bean.Channel;

/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目和分类的mapper
 * @author: charles
 * @date: 2020年4月2日 上午10:43:51
 */
public interface ChannelMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();

	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目ID 查询所有的分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);

}
