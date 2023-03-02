package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*セキュリティの対象外を設定*/
	@Override
	public void configure(WebSecurity web) throws Exception{
		web 
			.ignoring()
				.antMatchers("/webjars/**")
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
				.antMatchers("/login").permitAll() //直リンクok
				.antMatchers("/user/signup").permitAll() //直リンクok
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
	
	/*認証の設定*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		
		/*インメモリ処理
		auth
			.inMemoryAuthentication()
				.withUser("user")//userを追加
					.password(encoder.encode("user"))
					.roles("GENERAL")
				.and()
				.withUser("admin")
					.password(encoder.encode("admin"))
					.roles("ADMIN"); */
		//ユーザーデータで認証
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}
}
