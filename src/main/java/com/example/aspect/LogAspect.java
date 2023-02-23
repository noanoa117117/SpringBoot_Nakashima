package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
	/*
	 サービスの実行 前 にログ出力する
	 対象:[UserService]をクラス名に含んでいる
	 */
	@Before("execution(* *..*.*UserService.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("メソッド開始:"+jp.getSignature());
	}
	
	/*
	 サービスの実行 前 にログ出力する
	 対象:[UserService]をクラス名に含んでいる
	 */
	@After("execution(* *..*.*UserService.*(..))")
	public void endLog(JoinPoint jp) {
		log.info("メソッド終了:"+jp.getSignature());
	}
}
