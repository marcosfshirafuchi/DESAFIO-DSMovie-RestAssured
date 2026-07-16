package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ScoreControllerRA {


	private Long nonExistingMovieId, missingMovieId;
	private String adminUsername, adminPassword, clientUsername, clientPassword;
	private String adminToken, clientToken;

	//Criando a request body do post
	private Map<String, Object> putScoreInstance;

	@BeforeEach
	public void setUp() throws JSONException {
		baseURI = "http://localhost:8080";

		nonExistingMovieId = 100L;
		missingMovieId = null;

		//Definindo adminUsername e adminPassword
		adminUsername = "maria@gmail.com";
		adminPassword = "123456";
		//Definindo clientUsername e clientPassword
		clientUsername = "alex@gmail.com";
		clientPassword = "123456";

		//Obtendo o token de administrador
		adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
		//Obtendo o token de cliente
		clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);

		//Definindo o putScoreInstance
		putScoreInstance = new HashMap<>();
		putScoreInstance.put("movieId", 1);
		putScoreInstance.put("score", 4);
	}

	/*Abaixo estão os testes de API que você deverá implementar utilizando o RestAssured. O mínimo para aprovação no desafio são 8 dos 10 testes.

	MovieContollerRA:
	●	findAllShouldReturnOkWhenMovieNoArgumentsGiven
	●	findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty
	●	findByIdShouldReturnMovieWhenIdExists
	●	findByIdShouldReturnNotFoundWhenIdDoesNotExist
	●	insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle
	●	insertShouldReturnForbiddenWhenClientLogged
	●	insertShouldReturnUnauthorizedWhenInvalidToken

	ScoreContollerRA:
	●	saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist
	●	saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId
	●	saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero

	* */

	//●	saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist
	@Test
	public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {
		putScoreInstance.put("movieId", nonExistingMovieId);
		JSONObject newScore = new JSONObject(putScoreInstance);
		given()
			//Definindo o cabeçalho da requisição do header do método post do endpoint Login
			//Tipo da informação
			.header("Content-type","application/json")
			//Buscando o token de cliente
			.header("Authorization","Bearer " + clientToken)
			.body(newScore.toString())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/scores")
		.then()
			//Verificando a resposta da requisição
			.statusCode(404);
	}

	//●	saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId
	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {
		putScoreInstance.put("movieId", missingMovieId);
		JSONObject newScore = new JSONObject(putScoreInstance);
		given()
				//Definindo o cabeçalho da requisição do header do método post do endpoint Login
				//Tipo da informação
				.header("Content-type","application/json")
				//Buscando o token de cliente
				.header("Authorization","Bearer " + adminToken)
				.body(newScore.toString())
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.put("/scores")
				.then()
				//Verificando a resposta da requisição
				.statusCode(422);
	}

	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {
	}
}
