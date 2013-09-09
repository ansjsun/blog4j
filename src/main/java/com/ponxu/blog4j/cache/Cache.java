package com.ponxu.blog4j.cache;

import com.ponxu.blog4j.Config;
import com.ponxu.run.lang.StringUtils;

/**
 * 缓存管理(优先Memeche)
 * 
 * @author xwz
 */
public class Cache {

    public static Object get(String key) {
	if (Config.cache && StringUtils.isNotEmpty(key)) {
	    return RAMCache.get(key);
	}
	return null;
    }

    public static void put(String key, Object value) {
	if (Config.cache && StringUtils.isNotEmpty(key)) {
	    RAMCache.put(key, value);
	}
    }

    public static void clear() {
	RAMCache.clear();
    }
}
