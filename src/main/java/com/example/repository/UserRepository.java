package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.user.model.MUser;

public interface UserRepository extends JpaRepository<MUser,String>{

}
