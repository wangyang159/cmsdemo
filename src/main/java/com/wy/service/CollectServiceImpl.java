package com.wy.service;

import java.util.List;


import javax.annotation.Resource;

import com.wy.utils.StringUtil;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wy.bean.Collect;
import com.wy.dao.CollectMapper;
import com.wy.utils.CMSException;

@Service
public class CollectServiceImpl implements CollectService {
	@Resource
	private CollectMapper collectMapper;

	@Override
	public boolean insert(Collect collect) {
		
		if(!StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMSException("url 不合法");
		
		return collectMapper.insert(collect) >0;
	}

	@Override
	public PageInfo<Collect> selects(Collect collect, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Collect> selects = collectMapper.selects(collect);
		return new PageInfo<Collect>(selects);
	}

	@Override
	public int selectCount(String text, Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selectCount(text, userId);
	}

	@Override
	public int deleteCollect(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.deleteCollect(id);
	}

}
