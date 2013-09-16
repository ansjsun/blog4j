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
			server = Server.createTcpServer().start();
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

	public static void main(String[] args) throws UnknownHostException {
		new H2Server().startServer();
	}
}
