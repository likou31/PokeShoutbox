package com.amsellem.projet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComment; 
	private int idUser; 
	private String pseudoUser;
	private Date datePost;
	private String contenu;
	
	/**
	 * 
	 * @return
	 */
	public int getIdComment() {
		return idComment;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getIdUser() {
		return idUser;
	}
	
	/**
	 * 
	 * @param idUser
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPseudoUser() {
		return this.pseudoUser;
	}
	
	/**
	 * 
	 * @param pseudoUser
	 */
	public void setPseudoUser(String pseudoUser) {
		this.pseudoUser = pseudoUser;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getDatePost() {
		return datePost;
	}
	
	/**
	 * 
	 * @param datePost
	 */
	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getContenu() {
		return contenu;
	}
	
	/**
	 * 
	 * @param contenu
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}	
}
