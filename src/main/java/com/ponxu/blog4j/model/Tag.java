package com.ponxu.blog4j.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 标签
 * 
 * @author xwz
 */
@Table("bj_tag")
public class Tag implements java.io.Serializable {
	private static final long serialVersionUID = 3998059389560376840L;
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private int sort;
	@Column("post_count")
	private int postCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
}
