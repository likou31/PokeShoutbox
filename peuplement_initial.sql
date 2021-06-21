-- Insertion des 2 premiers commentaires

INSERT INTO `comment` (`id_comment`, `id_user`, `pseudo_user`, `date_post`, `contenu`) VALUES
(2, 2, 'pseudo1', '2021-05-19 20:26:11', 'Deuxième commentaire sauvegardé dans la base de données'),
(3, 2, 'pseudo1', '2021-05-20 09:29:40', 'https://start.spring.io/'),
(4, 2, 'pseudo1', '2021-05-20 09:46:55', 'http://127.0.0.1:8080/pikachu.png');

-- Insertion de 2 utilisateurs de bases

INSERT INTO `user` (`id`, `pseudo`, `password`) VALUES
(2, 'pseudo1', '$2a$10$uUebHeQh7QyecevXGJ7qd.MQw2ngKYutmI0g2BDLXMOjScNZ0FTtK'),
(11, 'oracle', '$2a$10$bdXjDpreT5tl83ybBPbVLuSsqOJUaUS3j8MuM06c/ZgQYlxg6Sxoe');
