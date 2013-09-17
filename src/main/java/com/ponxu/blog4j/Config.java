package com.ponxu.blog4j;

import java.util.ResourceBundle;

import org.nutz.castor.Castors;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.ponxu.blog4j.database.H2Server;

/**
 * 全局配置
 * 
 * @author xwz
 */
@Modules(scanPackage = true)
@SetupBy(Config.class)
@IocBy(type = ComboIocProvider.class, args = {
    "*org.nutz.ioc.loader.json.JsonLoader", "ioc.js",
    "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.ponxu"})
public class Config implements Setup {

	public static String theme = "simple2";
	public static int pageSize = 15;
	public static boolean cache = false;
	public static int sublength = 500;

	/** 通用配置 */
	private static void loadCommon() {
		// 基础设置
		ResourceBundle prop = ResourceBundle.getBundle("blog4j");
		theme = getProperty(prop, "theme", "simple");
		pageSize = Castors.me().castTo(getProperty(prop, "page_size", "15"), int.class);
		cache = Castors.me().castTo(getProperty(prop, "cache", "false"), Boolean.class);
		sublength = Castors.me().castTo(getProperty(prop, "sublength", "500"), int.class);
	}

	public static String getProperty(ResourceBundle prop, String key, String defaultValue) {
		try {
			return prop.getString(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultValue;
		}
	}

	public void init(NutConfig nc) {
		// TODO Auto-generated method stub
		loadCommon();
		H2Server.startServer();
	}

	public void destroy(NutConfig nc) {
		// TODO Auto-generated method stub
		H2Server.stopServer();
	}

}