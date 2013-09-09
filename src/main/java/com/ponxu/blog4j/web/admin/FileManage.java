package com.ponxu.blog4j.web.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import com.ponxu.run.log.Log;
import com.ponxu.run.log.LogFactory;
import com.ponxu.run.web.FileUpload;
import com.ponxu.run.web.FileUpload.FilePart;
import com.ponxu.run.web.WebApplication;

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
public class FileManage extends AdminOAuthHandler {
    private static final Log LOG = LogFactory.getLog();

    /** 附件目录 */
    private static final String ATTACHMENT_FOLDER = "attachment";
    /** 附件域名字 */
    private static final String FILEDATA_NAME = "filedata";

    @Override
    protected boolean oauth() {
	if ("get".equalsIgnoreCase(request().getMethod()))
	    return true;

	return super.oauth();
    }

    // 下载
    public void get(String fileName) {

    }

    // 上传
    public void post() {
	FileUpload fileUpload = new FileUpload(request());
	FilePart filePart = fileUpload.getFilePart(FILEDATA_NAME);

	String fileName = filePart.getFileName();
	File f = new File(fileName);
	fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + f.getName();

	// 直接写入本地存储(SAE FileWrap)
	try {
	    FileOutputStream out = new FileOutputStream(getSavePath(fileName));
	    out.write(filePart.getFileContent());
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    LOG.error(e.getMessage(), e);
	}

	renderString(getDownloadPath(fileName));
    }

    // 文件存储路径
    private String getSavePath(String fileName) {
	// 本地环境
	ServletContext sc = WebApplication.getInstnce().getServletContext();
	String path = sc.getRealPath("/" + ATTACHMENT_FOLDER);
	// 检测文件夹是否存在
	File f = new File(path);
	if (!f.exists()) {
	    f.mkdirs();
	}
	return path + "/" + fileName;
    }

    // 文件下载路径
    private String getDownloadPath(String fileName) {
	return getContextPath() + "/" + ATTACHMENT_FOLDER + "/" + fileName;
    }

}