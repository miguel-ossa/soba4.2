package com.perfmath.spring.soba.aop.aspectj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

@Component("jdbcConnection")
public class JDBCConnection {

	public Connection getConnection(String connectionDescriptor) {
		long startTime = System.nanoTime ();
		String[] part = new String[4];
		StringTokenizer st = new StringTokenizer(connectionDescriptor, "+");
		int i = 0;
		while (st.hasMoreElements()) {
			part[i] = st.nextElement().toString();
			i++;
		}
		System.out.println("   --> Parsing took  " + 
				(System.nanoTime () - startTime)/1000 +
				" microseconds inside JDBCConnection");
		Connection conn = null;
		try {
			Class.forName(part[0]).newInstance();
			conn = DriverManager.getConnection(part[1], part[2], part[3]);
			System.out.println("   --> Connect to database server " + part[1]
					+ " successful");
		} catch (Exception e) {
			System.out.println("Cannot connect to database server: "
					+ e.getMessage());
		}
		System.out.println("   --> getConnection took  " + 
		     (System.nanoTime () - startTime)/1000000 +
			" ms inside JDBCConnection");
		return conn;
	}

	public void returnConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("  --> Database connection terminated");
			} catch (Exception e) {
			}
		}
	}
}
