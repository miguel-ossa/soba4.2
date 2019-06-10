package com.perfmath.spring.soba.service;

public class SobaConfig {
	private static String databaseVendor;

	public static String getDatabaseVendor() {
		return databaseVendor;
	}

	public void setDatabaseVendor(String databaseVendor) {
		SobaConfig.databaseVendor = databaseVendor;
	}
}
