package com.amsellem.projet.controller;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amsellem.projet.model.Comment;
import com.amsellem.projet.service.CommentService;
import com.amsellem.projet.service.UserService;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


@RestController
@RequestMapping("/shoutbox")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @return une vue avec tout les commentaires
	 */
	@GetMapping
	public ModelAndView getAllComments(){
		ModelAndView modelAndView = new ModelAndView("comments");
	    modelAndView.addObject("comments", commentService.listAll());
	    modelAndView.addObject("users", userService.listAll());
	    return modelAndView;
	}
	
	/**
	 * Permet d'envoyer les commentaires dans la base de données
	 * @param req
	 */
	@PostMapping("/send")
	public void sendComment(HttpServletRequest req) {
		try {
			Object obj = new JSONParser().parse(req.getReader().lines().collect(Collectors.joining()));
			JSONObject json = (JSONObject) obj;
			Comment comment = new Comment();
			comment.setIdUser(2);
			comment.setDatePost(new Date());
			comment.setContenu(json.getAsString("contenu"));
			comment.setPseudoUser(req.getUserPrincipal().getName());
			commentService.saveComment(comment);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Recharger les commentaires à partir d'un JSON avec les commentaires mis à jour
	 * @return
	 */
	@GetMapping("/reload-comments")
	public String reload() {
		return commentService.getCommentsJSON().toJSONString();
	}
	
	/**
	 * Filtrage par pseudo
	 * @param req
	 * @return
	 */
	@GetMapping("/filter-comments-pseudo")
	public String filterCommentPseudo(HttpServletRequest req){
		return commentService.getCommentsByPseudoJSON(req.getHeader("pseudo")).toJSONString();
	}
	
	/**
	 * Filtrage par mot clé
	 * @param req
	 * @return
	 */
	@GetMapping("/filter-comments-mot-cle")
	public String filterCommentMotCle(HttpServletRequest req) {
		return commentService.getCommentsByMotCleJSON(req.getHeader("motCle")).toJSONString();
	}
	
	/**
	 * Filtre par pseudo et mot clé
	 * @param req
	 * @return
	 */
	@GetMapping("/filter-comments-pseudo-and-mot-cle")
	public String filterCommentPseudoAndMotCle(HttpServletRequest req) {
		return commentService.getCommentsByPseudoAndMotCleJSON(req.getHeader("pseudo"), req.getHeader("motCle")).toJSONString();
	}
}
