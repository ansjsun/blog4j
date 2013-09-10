package com.ponxu.blog4j.database;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.h2.util.Utils;

import com.ponxu.blog4j.Config;

public class H2Server {
    private Server server;

    public void startServer() {
	try {
	    System.out.println("正在启动h2...");
	    System.setProperty("h2.bindAddress", "127.0.0.1") ;
	    System.out.println("设置h2.bindAddress为 ： "+"127.0.0.1");
	    server = Server.createTcpServer(new String[] { "-tcpPort", Config.dbPort }).start();
	} catch (SQLException e) {
	    System.out.println("启动h2出错：" + e.toString());
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    public void stopServer() {
	if (server != null) {
	    System.out.println("正在关闭h2...");
	    server.stop();
	    System.out.println("关闭成功.");
	}
    }

    public void useH2() {
	try {
	    Class.forName("org.h2.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:h2:" + Config.dbDir, Config.dbUser, Config.dbPassword);
	    Statement stat = conn.createStatement();
	    // insert data
	    // stat.execute("DROP TABLE IF EXISTS TEST");
	    stat.execute("CREATE TABLE TEST(NAME VARCHAR)");
	    stat.execute("INSERT INTO TEST VALUES('Hello World')");
	    // use data
	    ResultSet result = stat.executeQuery("select name from test ");
	    int i = 1;
	    while (result.next()) {
		System.out.println(i++ + ":" + result.getString("name"));
	    }
	    result.close();
	    stat.close();
	    conn.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) throws UnknownHostException {
	new H2Server().startServer();
    }
}
