package com.ponxu.blog4j.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ponxu.blog4j.dao.BasicDao;
import com.ponxu.blog4j.util.MD5Util;

/**
 * 登录
 * 
 * @author xwz
 */
@IocBean
public class LoginAction {

	@Inject
	private BasicDao basicDao;

	@At("/admin/login")
	public Boolean login(@Param("username") String username, @Param("password") String password, HttpSession session) {
		username = MD5Util.MD5(username);
		password = MD5Util.MD5(password);

		if (checkLogin(username, password)) {
			session.setAttribute("LoginAdmin", true);
			return true;
		} else {
			return false;
		}
	}

	@Ok("fm:/")
	public void loginOut(HttpSession session) {
		session.setAttribute("LoginAdmin", false);
		session.removeAttribute("LoginAdmin");
	}

	/**
	 * 验证登陆吗是否正确
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private boolean checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		Cnd and = Cnd.where("username", "=", username).and("password", "=", password);
		List<Record> findListByCondition = basicDao.findListByCondition("bj_setting", and);
		if (findListByCondition.size() == 2) {
			return true;
		} else {
			return false;
		}
	}
}
