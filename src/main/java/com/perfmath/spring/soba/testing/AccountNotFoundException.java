package com.perfmath.spring.soba.testing;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String msg) {
		super(msg);
	}

	public AccountNotFoundException() {
		super();
	}
}
