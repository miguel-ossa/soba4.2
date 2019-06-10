package com.perfmath.spring.soba.util;
 import org.apache.log4j.Level;
 import org.apache.log4j.LogManager;
 import org.apache.log4j.Logger;
 import java.util.HashMap;
 import java.util.Enumeration;
 import java.util.Set;
 import java.util.Arrays;
public class Log4jAdmin {
public static void showLoggers () {

	String name = "";
	String effectiveLevel = "";
	String parent = "";
	
	Enumeration loggers = LogManager.getCurrentLoggers();
	while (loggers.hasMoreElements()) {
	
		Logger logger = (Logger) loggers.nextElement();
		name = logger.getName().toString();
		parent = logger.getParent().getName().toString();
	}
   Logger rootLogger = LogManager.getRootLogger();
}
public static void showRootLoggers () {

   Logger rootLogger = LogManager.getRootLogger();
}
}
