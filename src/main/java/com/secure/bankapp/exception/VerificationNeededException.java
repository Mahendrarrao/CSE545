package com.secure.bankapp.exception;

public class VerificationNeededException extends Exception {
private String msg;
	
	public VerificationNeededException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return msg;
	}
}
