package com.perfmath.spring.soba.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.perfmath.spring.soba.service.SobaConfig;

public class TestAppContext {

	public static void main (String[] args) {
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext context = new ClassPathXmlApplicationContext ("myBeans.xml");
		//ApplicationContext context = new FileSystemXmlApplicationContext ("src/main/resources/myBeans.xml");
		//SobaConfig sobaConfig = context.getBean("sobaConfig", SobaConfig.class);
		//System.out.println ("Database vendor for SOBA: " + sobaConfig.getDatabaseVendor());
		// no need to use the getBean method
		System.out.println ("Database vendor for SOBA: " + SobaConfig.getDatabaseVendor());
	}
}
