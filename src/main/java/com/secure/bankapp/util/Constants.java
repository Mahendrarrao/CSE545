package com.secure.bankapp.util;

public class Constants {
	
	public static final String VERIFY_NEEDED = "verifyNeeded";
	
	public static final String PASS_CHANGE = "changePass";
	
	public static final String USER_LOCKED = "userLocked";
	
	public static final String USER_ACTIVE = "active";
	
	public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
	
	public static final String ROLE_MERCHANT = "ROLE_MERCHANT";
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ROLE_TIER1 = "ROLE_TIER1";
	
	public static final String ROLE_TIER2 = "ROLE_TIER2";
	
	public static final Integer MAX_ATTEMPTS = 3;
	
	public static final String USER_LOGIN = "USER_LOGIN";
	
	public static final String USER_LOGOUT = "USER_LOGOUT";

	public static final String ACTIVE = "active";
	
	public static enum ACCOUNT_STATUS { ACTIVE, VERIFY_NEEDED, CLOSED}
	
	public static enum TRANSACTION_STATUS { COMPLETED, PENDING_APPROVAL, FAILED, REJECTED}
	
	public static enum TRANSACTION_TYPE { CREDIT, DEBIT}
	
	public static enum REQUEST_STATUS { COMPLETED, PENDING_APPROVAL, FAILED, REJECTED}
	
	public static enum REQUEST_TYPE { NEWACCOUNT, APPOINTMENT}
	
	public static final String	PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
	

	
	
	
	
	
	
	

}
