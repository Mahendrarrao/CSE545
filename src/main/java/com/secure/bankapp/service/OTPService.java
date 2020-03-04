package com.secure.bankapp.service;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

@Service
public class OTPService {
    private static final Integer EXPIRE_MINS = 2; // configuration can be changed later as per requirement
    private LoadingCache<String, Integer> oTPCache;
    public OTPService(){
        super();
        oTPCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public int generateOTP(String key){
        Random random = new Random();
        int otp = 10000 + random.nextInt(90000);
        oTPCache.put(key, otp);
        return otp;
    }

    public int getOtp(String key){
        try{
            return oTPCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }
    public void clearOTP(String key){
    	oTPCache.invalidate(key);
    }
}