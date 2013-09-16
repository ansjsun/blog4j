package com.ponxu.blog4j.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

import com.petebevin.markdown.MarkdownProcessor;

/**
 * 文章
 * 
 * @author xwz
 */
@Table("bj_post")
public class Post implements java.io.Serializable {
	private static final long serialVersionUID = -7679909637903047354L;

	public static final String STATUS_DRAFT = "draft";
	public static final String STATUS_PUBLISH = "publish";
	public static final String STATUS_PRIVATE = "private";

	// 类型
	public static final String TYPE_POST = "post";
	public static final String TYPE_PAGE = "page";

	@Id
	private int id;
	@Column
	private String url;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private Date addtime;
	@Column
	private int top;
	@Column
	private String status = STATUS_PUBLISH;
	@Column
	private String type = TYPE_POST;
	@ManyMany(target = Tag.class, relation = "bj_post_tag", from = "post_id", to = "tag_id")
	private Set<Tag> tags = new HashSet<Tag>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getContentHtml() {
		return new MarkdownProcessor().markdown(content);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", url=" + url + ", title=" + title + ", content=" + content + ", top=" + top + ", status=" + status + ", type=" + type + "]";
	}
}
