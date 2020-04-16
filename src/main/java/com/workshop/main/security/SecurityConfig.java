package com.workshop.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
//			
//		httpSecurity.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
//        .antMatchers("/index").permitAll()
//		.antMatchers("/game/", "/story/", "/game/**", "/story/**").hasAnyRole("admin", "superadmin")
//		.antMatchers("/topic/**").hasRole("superadmin")
//		.anyRequest().authenticated().and().httpBasic().and().logout().invalidateHttpSession(true)
//		.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
//		.accessDeniedHandler(accessDeniedHandler);
		
		httpSecurity.authorizeRequests().antMatchers("**").authenticated().anyRequest().permitAll().and().formLogin()
		.loginPage("/login").permitAll().and().logout()
		.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

		
		
	}
}