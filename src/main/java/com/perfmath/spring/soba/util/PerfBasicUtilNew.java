package com.perfmath.spring.soba.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class PerfBasicUtilNew {

	private String apfFileName;
	private String profilingStatus;
	private PrintWriter apfWriter;
	
	public String getApfFileName() {
		return apfFileName;
	}

	public void setApfFileName(String apfFileName) {
		this.apfFileName = apfFileName;
	}

	public String getProfilingStatus() {
		return profilingStatus;
	}

	public void setProfilingStatus(String profilingStatus) {
		this.profilingStatus = profilingStatus;
	}

	public  PrintWriter getApfWriter() {
		return apfWriter;
	}

	public  void setApfWriter(PrintWriter apfWriter) {
		this.apfWriter = apfWriter;
	}

	public long getSysTime() {
		return System.currentTimeMillis();
	}

	public int getThreadId() {
		int jvmId = 0;
		String JVMId = System.getProperty("jvmId");
		if (JVMId != null) {
			jvmId = Integer.parseInt(JVMId);
		}
		return jvmId;
	}

	public  void printLogData(String APISignature) {

		if (getProfilingStatus().equals("enabled")) {
			apfWriter.println("API" + "," + Thread.currentThread().getName()
					+ "," + System.currentTimeMillis() + "," + "unknownServer"
					+ "," + Thread.currentThread().getThreadGroup().getName()
					+ "," + "unknownClient" + "," + "unknownUser" + ","
					+ APISignature);
			apfWriter.flush();
		}
	}

	public void createApfWriter() {

		try {
			if (apfWriter == null) {
				apfWriter = new PrintWriter(new FileWriter(apfFileName, true));
			}
		} catch (IOException io) {
			System.out.println("Failed to create " + apfFileName + " "
					+ io.getMessage());
			System.exit(-1);
		}
		System.out.println("apfWriter created: ");
	}

	public void closeApfWriter() {

		if (apfWriter != null) {
			apfWriter.flush();
			apfWriter.close();
		}
	}

	public  void flushApfWriter() {

		if (apfWriter != null) {
			apfWriter.flush();
		}
	}

	public  String getPerfEntries(String clientId, String username,
			String delim) {
		String hostIp = "";
		try {
			hostIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String threadGroup = Thread.currentThread().getThreadGroup().getName();
		String perfEntries = delim + hostIp + delim + threadGroup + delim
				+ clientId + delim + username;
		return perfEntries;
	}
	// for non-proxy-based calls
	public  void log() {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		// int count = 0;
		// for (StackTraceElement e: stacktrace) {
		// apfWriter.println (stacktrace[2].toString());
		// count++;
		// }
		if (getProfilingStatus().equals("enabled")) {
			String data = "/API" + "," + Thread.currentThread().getName()
					+ "," + System.currentTimeMillis() + "," + "unknownServer"
					+ "," + Thread.currentThread().getThreadGroup().getName()
					+ "," + "unknownClient" + "," + "unknownUser" + ","
					+ stacktrace[2].toString();
			if (apfWriter != null) {
				apfWriter.println(data);
			} else {
				System.out.println (data);
			}
		}
	}
	// for proxy-based calls
	public void log(String message) {
		int index1 = message.indexOf("@");
		int index2 = message.lastIndexOf("@");
		if (index1 > 0) {
			message = message.substring (0, index1) + "." + message.substring (index2 + 1);
		}
		if (getProfilingStatus().equals("enabled")) {
			String data = "API" + "," + Thread.currentThread().getName()
					+ "," + System.currentTimeMillis() + "," + "unknownServer"
					+ "," + Thread.currentThread().getThreadGroup().getName()
					+ "," + "unknownClient" + "," + "unknownUser" + ","
					+ message;
			if (apfWriter != null) {
				apfWriter.println(data);
			} else {
				System.out.println (data);
			}
		}
	}
}
