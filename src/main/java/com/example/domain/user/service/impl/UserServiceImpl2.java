package com.example.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/*ユーザー登録*/
	@Transactional
	@Override
	public void signup(MUser user) {
		//存在チェック
		boolean exists = repository.existsById(user.getUserId());
		if(exists) {
			throw new DataAccessException("ユーザーが既に存在") {};
		}
		
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		//insert
		repository.save(user);
	}
	
	/*ユーザー取得*/
	@Override
	public List<MUser> getUsers(MUser user){
		return repository.findAll();
	}
	
	/*ユーザー取得1件*/
	@Override
	public MUser getUserOne(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;
	}
	
	/*ユーザー更新1件*/
	@Transactional
	@Override
	public void updateUserOne(String userId,String password,String userName) {
		
	}
	
	/*ユーザー削除(1件)*/
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		repository.deleteById(userId);
	} 
	
	/*ログインユーザー取得*/
	@Override
	public MUser getLoginUser(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;
	}
}
