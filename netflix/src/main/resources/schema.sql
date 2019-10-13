DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
  	name			TEXT NOT NULL,
  	details         TEXT NOT NULL,
  	type          	TEXT NOT NULL,
  	hasProblem      BIT   NOT NULL,
  	countWatched	BIGINT	NOT NULL
	countLiked		BIGINT	NOT NULL,
	countDisliked	BIGINT	NOT NULL);

INSERT INTO MOVIES (name, details, type, hasProblem, countWatched, countLiked, countDisliked)
VALUES ('O auto da compadecida', 'O Auto da Compadecida é um filme brasileiro de comédia dramática lançado em 2000. Dirigido por Guel Arraes e com roteiro de Adriana Falcão, João Falcão e do próprio diretor, o filme é baseado na peça teatral Auto da Compadecida de 1955 de Ariano Suassuna, com elementos de O Santo e a Porca e Torturas de um Coração, ambas do mesmo autor, e influências do clássico de Giovanni Boccaccio Decameron.[2] Durante o Grande Prêmio Cinema Brasil, evento criado pelo Ministério da Cultura, o filme recebeu as premiações de melhor diretor, melhor roteiro, melhor lançamento e melhor ator.[3] É o filme brasileiro de maior bilheteria de 2000, sendo visto por mais de dois milhões de espectadores.', 'Comédia', 0, 1, 1, 0);

DROP TABLE IF EXISTS users;

CREATE TABLE user (
  	username	TEXT NOT NULL,
  	password	TEXT NOT NULL);

INSERT INTO USERS (username, password)
VALUES ('Vinicius', '123456');
  	
DROP TABLE IF EXISTS myMovies;

CREATE TABLE myMovies (
	user_id		BIGINT,
	movie_id	BIGINT,
	liked		BIT);

INSERT INTO myMovies(user_id, movie_id, liked)
VALUES (1, 1, 1);

DROP TABLE IF EXISTS myFutureMovies;

CREATE TABLE myFutureMovies (
	user_id		BIGINT,
	movie_id	BIGINT);


