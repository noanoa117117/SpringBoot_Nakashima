package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*セキュリティの対象外を設定*/
	@Override
	public void configure(WebSecurity web) throws Exception{
		web 
			.ignoring()
				.antMatchers("/Webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/h2-console/**");
	}
	
	/*セキュリティの各種設定*/
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		//ログイン不要のページ設定
		http
			.authorizeRequests()
				.antMatchers("/login/**").permitAll() //直リンクok
				.antMatchers("/user/signup/**").permitAll() //直リンクok
				.anyRequest().authenticated(); //それ以外はNG
		
		//ログイン処理
		http
			.formLogin()	
				.loginProcessingUrl("/login")//ログイン処理
				.loginPage("/login")//ログインページの指定
				.failureUrl("/login?error")//ログイン失敗時の遷移先
				.usernameParameter("password")//ログインページのパスワード
				.defaultSuccessUrl("/user/list",true);//成功後の遷移先
				
				//CSRF対策を無効に
				http.csrf().disable();
	
	}
}
