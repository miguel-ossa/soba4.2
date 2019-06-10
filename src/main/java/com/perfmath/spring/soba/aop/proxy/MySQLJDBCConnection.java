package com.perfmath.spring.soba.aop.proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.StringTokenizer;

public class MySQLJDBCConnection implements IJDBCConnection {
	public Connection checkConnection(String connectionDescriptor) {
		Connection conn = null;
		long startTime = System.nanoTime ();
		String[] part = new String[4];
		StringTokenizer st = new StringTokenizer(connectionDescriptor, "+");
		int i = 0;
		while (st.hasMoreElements()) {
			part[i] = st.nextElement().toString();
			i++;
		}
        try {
			Class.forName(part[0]).newInstance();
			conn = DriverManager.getConnection(part[1], part[2], part[3]);
        }
        catch (Exception e) {
        	System.out.print ("Cannot connect to database server: " + e.getMessage());
		}
        return conn;
	}
}
