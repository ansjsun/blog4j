package com.ponxu.blog4j.web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.h2.util.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.ponxu.blog4j.dao.BasicDao;
import com.ponxu.blog4j.util.MD5Util;

@IocBean
public class InstallAction {

	@Inject
	private BasicDao basicDao;

	@At("/install")
	@Ok("jsp:install.jsp")
	public Boolean execute() {
		// 判断系统是否是初始化安装
System.out.println("aaaaaaaaaaaaa");
		if (basicDao.exists("bj_setting")) {
			return null;
		} else {
			return true;
		}
	}

	public String install(String title, String subtitle, String loginname, String loginpassword, String analyticscode, String commentcode) {

		String msg = null;
		if (!StringUtils.isNullOrEmpty(title) && !StringUtils.isNullOrEmpty(subtitle) && !StringUtils.isNullOrEmpty(loginname)
				&& !StringUtils.isNullOrEmpty(loginpassword)) {

			try {
				InputStream in = new FileInputStream("blog4j.sql");
				BufferedReader br = new BufferedReader(new InputStreamReader(in));

				String line = null;
				while ((line = br.readLine()) != null) {
					if (!StringUtils.isNullOrEmpty(line))
						basicDao.execute(line);
				}
				br.close();

				basicDao.execute("insert into bj_setting values('title','标题','" + title + "');");
				basicDao.execute("insert into bj_setting values('subtitle','子标题','" + subtitle + "');");
				basicDao.execute("insert into bj_setting values('username','登录用户名','" + loginname + "');");
				basicDao.execute("insert into bj_setting values('password','登录密码','" + MD5Util.MD5(loginpassword) + "');");
				basicDao.execute("insert into bj_setting values('analyticscode','统计代码','" + analyticscode + "');");
				basicDao.execute("insert into bj_setting values('commentcode','评论代码','" + commentcode + "');");

				//
				basicDao.execute("insert into bj_setting values('weibocode','微薄代码','');");
				basicDao.execute("insert into bj_setting values('sharecode','分享代码','');");
			} catch (Throwable t) {
				t.printStackTrace();
				msg = t.getMessage();
			}

		} else {
			msg = "博客标题, 博客副标题, 登录用户名, 登录密码, 必须填写!";
		}
		return msg;
	}
}
