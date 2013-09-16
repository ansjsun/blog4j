package com.ponxu.blog4j.web;

import java.util.List;

import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.ponxu.blog4j.dao.BasicDao;

/**
 * 设置
 * 
 * @author xwz
 */
@IocBean
public class SettingAction {

	@Inject
	private BasicDao basicDao;
	
	@At("/admin/setting")
	@Ok("")
	public List<Record> execute() {
		return basicDao.findListByCondition("bj_setting", null) ;
	}
}