package com.ponxu.blog4j;

import java.util.ResourceBundle;

import org.nutz.castor.Castors;
import org.nutz.mvc.annotation.Modules;

/**
 * 全局配置
 * 
 * @author xwz
 */
@Modules(scanPackage = true)
public class Config {

	public static String theme = "simple2";
	public static int pageSize = 15;
	public static boolean cache = false;
	public static int sublength = 500;
	
	public static String dbDir = "~/h2db/sample" ;

	static {
		// 加载配置
		loadCommon();
	}

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

}