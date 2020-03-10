package com.spandiar.security.springsecurityexercises.authenticator;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	
//	// below method is for authentication - INMEMORY
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("tannu")
//		.password("kannama")
//		.roles("ADMIN")
//		.and()
//		.withUser("spandiar")
//		.password("password")
//		.roles("AGENT");
//	}
	
//	//Authentication - using JDBC but with H2 DB
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.withDefaultSchema()
//			.withUser(
//					User.withUsername("tannu")
//						.password("tannu")
//						.roles("ADMIN")
//					)
//			.withUser(
//					User.withUsername("karthika")
//						.password("karthika")
//						.roles("WIFEY", "AGENT"));
//	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).getUserDetailsService();
		
	}
	
	// below method is for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		  http.authorizeRequests() 
				  .antMatchers("/api/site/admin").hasRole("ADMIN")
				  .antMatchers("/api/site/user").hasAnyRole("USER", "ADMIN")
				  .antMatchers("/api/site/wifey").hasRole("WIFEY")
				  .antMatchers("/api/site/about").permitAll()
				  .antMatchers("/api/userprofile/*").permitAll() 
				  .antMatchers("/*").permitAll()
			  .and()
			  .formLogin()
			  .and()
			  .csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
