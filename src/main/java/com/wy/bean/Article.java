package com.wy.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 
 * @ClassName: Article 
 * @Description: 文章内容表
 * @author: charles
 * @date: 2020年3月3日 上午11:25:22
 */
@Document(indexName="articles",type="article")
public class Article implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;//主键
	@Field(index=true,store=true,analyzer="ik_max_word",searchAnalyzer="ik_max_word",type=FieldType.text)
	private String title;//文章标题
	private String summary;//文章摘要
	@Field(index=true,store=true,analyzer="ik_max_word",searchAnalyzer="ik_max_word",type=FieldType.text)
	private String content;//文章内容
	private String picture;//文章的标题图片
	
	private Integer channelId;//所属栏目ID
	private Integer categoryId;//所属分类ID
	private Integer userId;//文章发布人ID
	
	private Integer hits;//  点击量
	private Integer hot;//是否热门文章   1：热门     , 0 ：一般文章
	private Integer status;//文章审核状态     0：待审        1：审核通过     -1: 审核未通过
	private Integer deleted;// 删除状态 0:正常，1：逻辑删除
	private Date created;// 文章发布时间
	private  Date  updated;// 文章修改时间
	
	private String contentType ;//文章内容类型  0:html  1:json
	private Channel channel;
	private Category category;
	private User user;
	
	
	private String keywords;//文章关键词
	private String original;//文章来源
	
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", summary=" + summary + ", content=" + content + ", picture="
				+ picture + ", channelId=" + channelId + ", categoryId=" + categoryId + ", userId=" + userId + ", hits="
				+ hits + ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", created=" + created
				+ ", updated=" + updated + ", contentType=" + contentType + ", channel=" + channel + ", category="
				+ category + ", user=" + user + ", keywords=" + keywords + ", original=" + original + "]";
	}

	
}
