package com.wy.es;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wy.bean.Article;

/**
 * 所有ES仓库接口必须继承ElasticSearchRepository,之后就自动具备了简单的CRUD的方法
 * 第一个泛型是文档操作的实例Bean  第二个是这个Bean的主键类型
 */
public interface ArticleElasticsearch extends ElasticsearchRepository<Article, Integer>{
	//按标题与内容查询
	List<Article> findByTitleOrContent(String title,String content);
}
