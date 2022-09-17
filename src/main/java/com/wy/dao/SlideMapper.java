package com.wy.dao;

import java.util.List;

import com.wy.bean.Slide;

public interface SlideMapper {

	/**
	 * 查询所有广告
	 * @return
	 */
	List<Slide> getAll();
}
