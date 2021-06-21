package com.amsellem.projet.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amsellem.projet.model.User;
import com.amsellem.projet.repository.UserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 
	 * @return Liste de tout les utilisateurs
	 */
	public List<User> listAll(){
		return userRepository.findAll();
	}
	
	/**
	 * Sauvegarde un utilisateur en base de données
	 * @param user
	 */
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	/**
	 * Supprime un utilisateur de la base de données
	 * à partir de l'id passé en paramètre
	 * @param userId
	 * @return booléen confirmant la suppression
	 */
	public boolean deleteUser(int userId) {
		userRepository.deleteById(userId);
		return true;
	}
	
	/**
	 * 
	 * @param id
	 * @return user dont l'id correspont à celui passé en paramètre
	 */
	public User getUserById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
}
