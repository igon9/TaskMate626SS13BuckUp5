package com.example.taskmate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//	@Bean
//	public UserDetailsService userDetailsService() {
//
//		UserDetails aaaa = User.withUsername("aaaa")
//				.password("{noop}aaaapass")
//				.roles("ADMIN")
//				.build();
//
//		UserDetails bbbb = User.withUsername("bbbb")
//				.password("{noop}bbbbpass")
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(aaaa, bbbb);
//
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests(
				auth -> auth
						.requestMatchers("/css/*").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated()
			)
			.formLogin(
					form -> form
					.loginPage("/login")
					.permitAll()
					.failureUrl("/login-error")
					.defaultSuccessUrl("/", true)
			)
			.exceptionHandling(ex -> ex.accessDeniedPage("/access-error"));

		return  http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}