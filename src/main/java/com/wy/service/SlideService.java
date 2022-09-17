package com.wy.service;

import java.util.List;

import com.wy.bean.Slide;

public interface SlideService {
	/**
	 * 查询所有广告
	 * @return
	 */
	List<Slide> getAll();
}
