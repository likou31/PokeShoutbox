package com.amsellem.projet.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amsellem.projet.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
	/**
	 * 
	 */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	return new CustomUserDetailsService();
    }
 
    /**
     * 
     * @return BCryptPasswordEncoder()
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    /**
     * 
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
 
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	
        http.authorizeRequests()
        	.antMatchers("/shoutbox").authenticated()
        	.antMatchers("/shoutbox/send").authenticated()
        	.antMatchers("/my-account").authenticated()
        	.antMatchers("/moderation").authenticated()
        	.antMatchers("/access-moderation").authenticated()
        	.antMatchers("/register").anonymous()
        	.antMatchers("/login").anonymous()
        	.antMatchers("/confirm-registration").anonymous()
        	.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        	.anyRequest().permitAll()
        	.and()
        	.formLogin()//
        		.loginPage("/login")
                .defaultSuccessUrl("/shoutbox")
                .permitAll()
                // Config for Logout Page
             .and()
             .logout().logoutSuccessUrl("/");
        
 
 
    }
 
 
}
