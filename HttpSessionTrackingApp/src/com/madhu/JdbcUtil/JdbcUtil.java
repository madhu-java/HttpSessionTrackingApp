package com.madhu.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
	public static Connection getJdbcConnection() throws SQLException {
		HikariConfig hikariConfig = new HikariConfig("C:\\Users\\madha\\Desktop\\eclipse-projects\\HttpSessionTrackingApp\\src\\com\\madhu\\properties\\application.properties");
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource.getConnection();
		
	}

}
