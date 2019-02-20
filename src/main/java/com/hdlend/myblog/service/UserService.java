package com.hdlend.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdlend.myblog.dao.UserDao;
import com.hdlend.myblog.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User findUserByLoginName(String loginName) {
		return userDao.findByUName(loginName);
	}

}
