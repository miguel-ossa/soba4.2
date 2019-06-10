package com.perfmath.spring.soba.util;

public  class MyLogger {
	private String name = "";
	private String effectiveLevel = "";
	private String parent = "";
	
	public MyLogger(String name, String effectiveLevel, String parent) {
		super();
		this.name = name;
		this.effectiveLevel = effectiveLevel;
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEffectiveLevel() {
		return effectiveLevel;
	}
	public void setEffectiveLevel(String effectiveLevel) {
		this.effectiveLevel = effectiveLevel;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

}	
