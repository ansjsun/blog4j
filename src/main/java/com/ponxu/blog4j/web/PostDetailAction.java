package com.ponxu.blog4j.web;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.Ok;

import com.ponxu.blog4j.dao.BasicDao;
import com.ponxu.blog4j.model.Post;

/**
 * 打开文章extends Blog
 * 
 * @author xwz
 */
@IocBean
public class PostDetailAction {

	@Inject
	private BasicDao basicDao;
	
	@Ok("/post/?")
	public Post get(int id) {
		return basicDao.find(id, Post.class); 
	}
}