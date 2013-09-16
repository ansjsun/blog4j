package com.ponxu.blog4j.web;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.ponxu.blog4j.dao.BasicDao;
import com.ponxu.blog4j.model.Post;

@IocBean
public class PostAction {
	@Inject
	private BasicDao basicDao;

	@DELETE
	@Ok("")
	public void delete(int id) {
		basicDao.delById(id, Post.class);
	}

	@GET
	@Ok("")
	public Post editer(int id) {
		return basicDao.find(id, Post.class);
	}

	@POST
	@Ok("")
	public void save(@Param("..") Post post) {
		this.basicDao.save(post);
	}
}
