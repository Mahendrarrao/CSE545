package com.secure.bankapp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "account")
public class Account {
	
	private Long accountId;
	private String accountType;
	private Double roi = 3.0;
	private Double balance;
	private Long routingNumber;
	private Date createdDate;
	private Date updatedDate;
	private String accountStatus;
	private String userId;
	private boolean defaultAccount;
	private String name;
	
	
	
	@Transient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "default_account")
	public boolean isDefaultAccount() {
		return defaultAccount;
	}
	public void setDefaultAccount(boolean defaultAccount) {
		this.defaultAccount = defaultAccount;
	}
	@Column(name="account_userid")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="account_status")
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	@Id
	@Column(name="account_id")
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	@Column(name="account_type")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Column(name="account_roi")
	public Double getRoi() {
		return roi;
	}
	public void setRoi(Double roi) {
		this.roi = roi;
	}
	@Column(name="account_balance")
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Column(name="account_routing")
	public Long getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(Long routingNumber) {
		this.routingNumber = routingNumber;
	}
	@Column(name="account_created")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="account_updated")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
	

}
