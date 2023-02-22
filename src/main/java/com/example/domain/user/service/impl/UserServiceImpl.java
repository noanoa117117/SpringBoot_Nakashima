package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<MUser> getUsers(MUser user){
		return mapper.findMany(user);
	}
	
	/*ユーザー取得(1件)*/
	@Override
	public MUser getUserOne(String UserId) {
		return mapper.findOne(UserId);
	}
		
	//ユーザー更新
	@Transactional
	@Override
	public void updateUserOne(String userId, String password,String userName) {
		mapper.updateOne(userId, password, userName);
		
		//(例外)int i = 1/0;
	}
	
	//ユーザー削除
	@Override
	public void deleteUserOne(String userId) {
		int count = mapper.deleteOne(userId);
	}
}
