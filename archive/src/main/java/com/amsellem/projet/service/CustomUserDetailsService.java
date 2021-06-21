package com.amsellem.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amsellem.projet.details.CustomUserDetails;
import com.amsellem.projet.model.User;
import com.amsellem.projet.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Utilisateur non trouvé");
		}
		return new CustomUserDetails(user);
	}

}
