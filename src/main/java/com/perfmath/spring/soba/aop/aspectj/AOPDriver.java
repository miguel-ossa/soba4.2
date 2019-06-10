package com.perfmath.spring.soba.aop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Connection;

import com.perfmath.spring.soba.aop.aspectj.ManageJDBCConnection;

public class AOPDriver {
	public static void main(String[] args) {
		{
			// PerfBasicUtil.setProfilingStatus("enabled");
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"springaopaspectj.xml");
			//ApplicationContext context = new FileSystemXmlApplicationContext ("springaopaspectj.xml");
			ManageJDBCConnection jdbcConnection = (ManageJDBCConnection) context
					.getBean("manageJDBCConnection");
			String connectionDescriptor = "com.mysql.cj.jdbc.Driver+"
					+ "jdbc:mysql://localhost:3308/soba+" + "sobaadmin+"
					+ "sobaadmin";
			Connection conn = jdbcConnection.openConnection(connectionDescriptor);
			jdbcConnection.closeConnection(conn);
		}
	}
}
