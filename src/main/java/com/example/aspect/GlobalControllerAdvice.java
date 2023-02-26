package com.example.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

	/*データベース関連の例外処理*/
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e,Model model){
		//から文字をセット
		model.addAttribute("error","");
		
		//メッセージをmodelに登録
		model.addAttribute("message","DataAccessExceptionが発生しました");
		
		//HTTPのエラーコード500をmodelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	/*その他の例外処理*/
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e,Model model) {
		//から文字をセット
		model.addAttribute("error","");
		
		//メッセージをmodelに登録
		model.addAttribute("message","Exceptionが発生しました");
		
		//Httpのエラーコード500をmodelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}
