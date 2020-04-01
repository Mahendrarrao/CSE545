package com.secure.bankapp.model;

import java.sql.Date; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_log")
public class SystemLog implements Comparable<SystemLog>{

	private Long Id;
	private Date timestamp;
	private String userId;
	private String message;
	
	public SystemLog() {
		
	}
	
	public SystemLog(String userId, String message, Date timestamp) {
		// TODO Auto-generated constructor stub
		this();
		this.userId = userId;
		this.message = message;
		this.timestamp = timestamp;
	}
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	@Column(name = "log_timestamp")
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Column(name = "related_user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int compareTo(SystemLog o) {
		if (getTimestamp() == null || o.getTimestamp() == null) {
			return 0;
	    }
		return getTimestamp().compareTo(o.getTimestamp());
	}	
}
