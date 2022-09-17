package com.wy.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Channel 
 * @Description: 文章栏目表
 * @author: charles
 * @date: 2020年3月3日 上午11:21:30
 */
public class Channel implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String name;//栏目的名称
	private String description;//描述
	private String icon;//栏目图标
	private Integer sorted;//排序
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSorted() {
		return sorted;
	}
	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}
	
	
}
