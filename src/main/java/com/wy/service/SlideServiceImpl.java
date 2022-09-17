package com.wy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wy.bean.Slide;
import com.wy.dao.SlideMapper;

@Service
public class SlideServiceImpl implements SlideService {

	@Resource
	private SlideMapper slideMapper;

	@Override
	public List<Slide> getAll() {
		return slideMapper.getAll();
	}
	
	
}
