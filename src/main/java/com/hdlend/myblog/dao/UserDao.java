package com.hdlend.myblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdlend.myblog.entity.User;

public interface UserDao extends JpaRepository<User,String>{

	public User findByUName(String uName);
	
}
