package com.rubypaper.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests(auth->auth.requestMatchers("/board/**").authenticated()
											.requestMatchers("/admin/**").hasRole("ADMIN")
											.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.loginPage("/system/login").defaultSuccessUrl("/board/getBoardList",true));
//		http.httpBasic(basic->basic.disable());
		
//		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/system/accessDenied"));
		
		http.logout(logout->logout.logoutUrl("/system/logout").invalidateHttpSession(true)
								.deleteCookies("JSESSIONID").logoutSuccessUrl("/"));
		
		return http.build();
	}

}
