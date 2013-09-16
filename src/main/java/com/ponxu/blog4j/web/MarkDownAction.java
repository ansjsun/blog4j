package com.ponxu.blog4j.web;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
public class MarkDownAction{
	@Ok("/markDown")
	public String execute(@Param("content") String content){
		return content ;
	}
}
