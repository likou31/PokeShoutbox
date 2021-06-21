package com.amsellem.projet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.amsellem.projet.service.CommentService;


@RestController
public class ModerationController {
	
	@Autowired
	private CommentService commentService;

	/**
	 * Affiche la vue de la page de modération
	 * @param req
	 * @return
	 */
	@RequestMapping("/moderation")
	public ModelAndView moderation(HttpServletRequest req) {
		if (req.getParameter("pw").equals("moderationPassword")) {
			return new ModelAndView("moderation");
		}else {
			return new ModelAndView("moderationfail");
		}
		
	}
	
	/**
	 * Affiche la vue pour l'accès à la page de modération
	 * @return
	 */
	@GetMapping("/access-moderation")
	public ModelAndView accessModeration() {
		return new ModelAndView("access-moderation");
	}
	
	/**
	 * Requête permettant la suppression d'un commentaire 
	 * à partir du panneau de modération
	 * @param req
	 * @return
	 */
	@RequestMapping("/delete-comment")
	public RedirectView deleteComment(HttpServletRequest req) {
		commentService.deleteComment(Integer.parseInt(req.getParameter("id-comment")));
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("/moderation?pw=moderationPassword");
	    return redirectView;
	}
}
