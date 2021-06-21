package com.amsellem.projet.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;


public class WebSecurity {
	/**
	 * 
	 * @param request
	 * @return boolean 
	 */
	@Bean
	public boolean checkModeration(HttpServletRequest request) {
    	return (request.getAttribute("pw") == "moderationPassword");
    }
    
}
