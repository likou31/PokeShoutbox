package com.amsellem.projet.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amsellem.projet.dtomodel.UserDTO;
import com.amsellem.projet.model.User;
import com.amsellem.projet.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Affiche vue de l'index
	 * @return
	 */
	@GetMapping
	public ModelAndView home() {
		return new ModelAndView("index");
	}
	
	/**
	 * Affiche vue avec formulaire d'inscription
	 * @return
	 */
	@GetMapping("/register")
	public ModelAndView registerForm() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		
		return model;
	}
	
	/**
	 * Affiche la vue permettant la connexion
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	/**
	 * Affiche la vue confirmant que l'inscription s'est bien faite
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/confirm-registration")
	public ModelAndView registerUser(UserDTO userDTO) {
		User newUser = new User();
		newUser.setPseudo(userDTO.getPseudo());
		ModelAndView model = new ModelAndView("register-success");
		newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
		userService.saveUser(newUser);
		return model;
	}
}
