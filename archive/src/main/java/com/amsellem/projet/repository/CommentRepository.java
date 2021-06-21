package com.amsellem.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amsellem.projet.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query("SELECT c FROM Comment c WHERE c.pseudoUser LIKE %?1%")
	Comment[] findCommentByUsername(String username);
	
	@Query("SELECT c FROM Comment c WHERE c.contenu LIKE %?1%")
	Comment[] findCommentByMotCle(String motCle);
	
	@Query("SELECT c FROM Comment c WHERE c.pseudoUser LIKE %?1% AND c.contenu LIKE %?2%")
	Comment[] findCommentByPseudoAndMotCle(String pseudo, String motCle);
}

