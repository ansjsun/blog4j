package com.ponxu.blog4j.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

import com.ponxu.blog4j.dao.BasicDao;
import com.ponxu.blog4j.model.Post;

/**
 * 首页
 * 
 * @author xwz
 */
@IocBean
public class IndexAction {

	@Inject
	private BasicDao basicDao;

	@At("/")
	public void execute(HttpServletRequest request) {
		List<Post> posts = basicDao.searchByPage(Post.class, 1, 15, "id");
		request.setAttribute("posts", posts);
	}

}