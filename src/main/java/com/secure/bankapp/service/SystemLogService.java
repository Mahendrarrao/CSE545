package com.secure.bankapp.service;

import java.util.List;

import com.secure.bankapp.model.SystemLog;

public interface SystemLogService {
	
	void recordLog(SystemLog log);
	void deleteLog(SystemLog log);
	List<SystemLog> getLogByUserId(String userId);
	
	List<SystemLog> getAllLogs();

}
