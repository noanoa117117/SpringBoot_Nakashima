package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	/*ユーザー登録*/
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL"); //ロール
		mapper.insertOne(user);
	}
	
	/*ユーザー取得*/
	@Override
	public List<MUser> getUsers(){
		return mapper.findMany();
	}
	
	/*ユーザー取得(1件)*/
	@Override
	public MUser getUserOne(String UserId) {
		return mapper.findOne(UserId);
	}
	
}
