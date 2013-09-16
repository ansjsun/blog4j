package com.ponxu.blog4j.web;

import org.apache.log4j.Logger;

/**
 * 文件上传下载<br>
 * <br>
 * 
 * <b>1 本地环境</b><br>
 * 上传: 存在在webapp/attachment目录<br>
 * 下载: /attachment/filename<br>
 * <br>
 * 
 * <b>2 SAE</b><br>
 * 上传: 存在Storage, 通过File Wrap操作<br>
 * 下载: http://appname-domain.stor.sinaapp.com/fileName<br>
 * <br>
 * 
 * <b>3 Cloud Foundry</b><br>
 * 上传: 存在Mongodb<br>
 * 下载: /file/filename<br>
 * 
 * @author xwz
 */
public class FileManage {
	private static final Logger LOG = Logger.getLogger(FileManage.class);

	/** 附件目录 */
	private static final String ATTACHMENT_FOLDER = "attachment";
	/** 附件域名字 */
	private static final String FILEDATA_NAME = "filedata";

	// 下载
	public void get(String fileName) {

	}

	// 上传
	public void post() {

	}

	// 文件存储路径
	private String getSavePath(String fileName) {
		return null;
	}

	// 文件下载路径
	private String getDownloadPath(String fileName) {
		return null;
	}

}