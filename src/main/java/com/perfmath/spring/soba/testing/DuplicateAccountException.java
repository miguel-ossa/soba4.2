package com.perfmath.spring.soba.testing;

public class DuplicateAccountException extends RuntimeException {
	public DuplicateAccountException(String msg) {
		super(msg);
	}

	public DuplicateAccountException() {
		super();
	}
}
