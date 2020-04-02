package com.secure.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.repository.SystemLogRepository;

@Service
public class SystemLogServiceImpl implements SystemLogService {

	@Autowired
	private SystemLogRepository systemLogRepository;
	@Override
	public void recordLog(SystemLog log) {
		// TODO Auto-generated method stub
		systemLogRepository.save(log);
	}

	@Override
	public void deleteLog(SystemLog log) {
		// TODO Auto-generated method stub
		
		systemLogRepository.delete(log);
		

	}

	@Override
	public List<SystemLog> getLogByUserId(String userId) {
		// TODO Auto-generated method stub
		return systemLogRepository.findByUserId(userId);
	}
	@Override
    public List<SystemLog> getAllLogs() {
        return systemLogRepository.findAll();
    }
}
