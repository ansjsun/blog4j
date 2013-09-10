package com.ponxu.blog4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ponxu.run.lang.BeanUtils;
import com.ponxu.run.lang.FileUtils;

/**
 * 全局配置
 * 
 * @author xwz
 */
public class Config {
    private static final String DEFAULT_PROP_FILE = "blog4j.properties";

    public static String theme = "simple2";
    public static int pageSize = 15;
    public static boolean cache = false;
    public static int sublength = 500;

    
    //h2 database path
    public static String dbDir = "./h2db/sample";
    public static String dbPort = "9080";
    public static String dbUser = "ansj";
    public static String dbPassword = "wysxzw";

    static {
	// 加载配置
	loadCommon();
    }


    /** 通用配置 */
    private static void loadCommon() {
	try {
	    Properties prop = new Properties();
	    InputStream in = FileUtils.fromClassPath(DEFAULT_PROP_FILE);
	    prop.load(in);

	    // 基础设置
	    theme = prop.getProperty("theme", "simple");
	    pageSize = BeanUtils.cast(prop.getProperty("page_size", "15"), int.class);
	    cache = BeanUtils.cast(prop.getProperty("cache", "false"), Boolean.class);
	    sublength = BeanUtils.cast(prop.getProperty("sublength", "500"), int.class);
	    
	    dbDir = prop.getProperty("dbDir", "./h2db/sample");
	    dbPort = prop.getProperty("dbPort", "9080");
	    dbUser = prop.getProperty("dbUser", "ansj");;
	    dbPassword = prop.getProperty("dbPassword", "wysxzw");;

	    in.close();
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	}
    }

}