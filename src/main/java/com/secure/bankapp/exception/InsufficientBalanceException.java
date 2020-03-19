package com.secure.bankapp.exception;

public class InsufficientBalanceException extends Exception {
	
	private String msg;
	
	public InsufficientBalanceException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return msg;
	}
	
	
}
