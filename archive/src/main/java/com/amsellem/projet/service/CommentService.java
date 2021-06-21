package com.amsellem.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amsellem.projet.model.Comment;
import com.amsellem.projet.repository.CommentRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@Service("commentService")
public class CommentService {
	
	private static final String ID = "id";
	private static final String IDUSER = "idUser";
	private static final String PSEUDOUSER = "pseudoUser";
	private static final String DATEPOST = "datePost";
	private static final String CONTENU = "contenu";
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 
	 * @return tout les commentaires
	 */
	public List<Comment> listAll(){
		return commentRepository.findAll();
	}
	
	/**
	 * sauvegarde un commentaire en base de données
	 * @param comment
	 */
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}

	/**
	 * Supprimer un commentaire de la base de données
	 * @param id
	 * @return booléen indiquant la suppresion
	 */
	public boolean deleteComment(int id) {
		commentRepository.deleteById(id);
		return true;
	}
	
	/**
	 * Retourne un commentaire par son id
	 * @param id
	 * @return Potentiellement un commentaire
	 */
	public Optional<Comment> findById(int id) {
		return commentRepository.findById(id);
	}
	
	/**
	 * 
	 * @return Tableau de JSON content les commentaires
	 */
	public JSONArray getCommentsJSON() {
		JSONArray array = new JSONArray();
		for (Comment com : commentRepository.findAll()) {
			JSONObject obj = new JSONObject();
			obj.put(ID, com.getIdComment());
			obj.put(IDUSER, com.getIdUser());
			obj.put(PSEUDOUSER, com.getPseudoUser());
			obj.put(DATEPOST, com.getDatePost().toString());
			obj.put(CONTENU, com.getContenu());
			array.add(obj);
		}
		return array;
	}
	
	/**
	 * 
	 * @param pseudo
	 * @return Tableau de JSON avec les commentaires dont
	 * le pseudo est similaire à celui passé en paramètre
	 */
	public JSONArray getCommentsByPseudoJSON(String pseudo) {
		JSONArray array = new JSONArray();
		for (Comment com : commentRepository.findCommentByUsername(pseudo)){
			JSONObject obj = new JSONObject();
			obj.put(ID, com.getIdComment());
			obj.put(IDUSER, com.getIdUser());
			obj.put(PSEUDOUSER, com.getPseudoUser());
			obj.put(DATEPOST, com.getDatePost().toString());
			obj.put(CONTENU, com.getContenu());
			array.add(obj);
		}
		return array;
	}
	
	/**
	 * 
	 * @param motCle
	 * @return Tableau JSON des commentaires contenant le 
	 * mot clé passé en paramètre
	 */
	public JSONArray getCommentsByMotCleJSON(String motCle) {
		JSONArray array = new JSONArray();
		for (Comment com : commentRepository.findCommentByMotCle(motCle)){
			JSONObject obj = new JSONObject();
			obj.put(ID, com.getIdComment());
			obj.put(IDUSER, com.getIdUser());
			obj.put(PSEUDOUSER, com.getPseudoUser());
			obj.put(DATEPOST, com.getDatePost().toString());
			obj.put(CONTENU, com.getContenu());
			array.add(obj);
		}
		return array;
	}
	
	/**
	 * 
	 * @param pseudo
	 * @param motCle
	 * @return Tableau JSON des commentaires triés 
	 * en fonction du pseudo et du mot clé passé en paramètre
	 */
	public JSONArray getCommentsByPseudoAndMotCleJSON(String pseudo, String motCle) {
		JSONArray array = new JSONArray();
		for (Comment com : commentRepository.findCommentByPseudoAndMotCle(pseudo, motCle)){
			JSONObject obj = new JSONObject();
			obj.put(ID, com.getIdComment());
			obj.put(IDUSER, com.getIdUser());
			obj.put(PSEUDOUSER, com.getPseudoUser());
			obj.put(DATEPOST, com.getDatePost().toString());
			obj.put(CONTENU, com.getContenu());
			array.add(obj);
		}
		return array;
	}
}
