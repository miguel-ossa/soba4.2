package com.perfmath.spring.soba.aop.aspectj;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("manageJDBCConnection")
public class ManageJDBCConnection {
	private JDBCConnection jdbcConnection;

	public Connection openConnection(String connectionDescriptor) {
		Connection conn = jdbcConnection.getConnection(connectionDescriptor);
		return conn;
	}

	public void closeConnection(Connection conn) {
		jdbcConnection.returnConnection(conn);
	}

	@Autowired
	public void setJdbcConnection(JDBCConnection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}
}
