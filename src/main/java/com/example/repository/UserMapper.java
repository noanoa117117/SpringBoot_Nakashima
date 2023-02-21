package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/*ユーザー登録*/
	public int insertOne(MUser user);
	
	/*ユーザー取得*/
	public List<MUser> findMany();
	
	/*ユーザー取得(1件)*/
	public MUser findOne(String userId);
}
