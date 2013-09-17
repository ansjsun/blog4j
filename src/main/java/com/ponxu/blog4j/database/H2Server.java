package com.ponxu.blog4j.database;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Server {
	private static Server server;

	public static void startServer() {

		if (server != null && server.isRunning(true)) {
			return;
		}
		try {
			System.out.println("正在启动h2...");
			server = Server.createTcpServer().start();
		} catch (SQLException e) {
			System.out.println("启动h2出错：" + e.toString());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void stopServer() {
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
