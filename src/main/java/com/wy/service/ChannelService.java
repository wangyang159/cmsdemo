package com.wy.service;

import java.util.List;

import com.wy.bean.Category;
import com.wy.bean.Channel;



public interface ChannelService {
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
