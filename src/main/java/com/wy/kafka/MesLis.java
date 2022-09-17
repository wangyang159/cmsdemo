package com.wy.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.wy.bean.Article;
import com.wy.dao.ArticleMapper;

/**
 *@描述
 * SSM整合消费者用的类 实现MessageListener<String, String>接口 泛型一般都是String
 *
 *@参数
 *@返回值
 *@创建人 wangyang
 *@创建时间 2022/9/17
 *@修改人和其它信息
 */
public class MesLis implements MessageListener<String, String>{

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String d = data.value();
		System.out.println("接收到的数据为"+d);

	}

}
