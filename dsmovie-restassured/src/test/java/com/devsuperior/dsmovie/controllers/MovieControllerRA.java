package com.devsuperior.dsmovie.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class MovieControllerRA {


	@BeforeEach
	public void setUp() {
		//Endereço que vai estar hospedado o serviço
		baseURI = "http://localhost:8080";
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

	//Abaixo estão os testes de API que você deverá implementar utilizando o RestAssured.
	//O mínimo para aprovação no desafio são 8 dos 10 testes.
	//●	findAllShouldReturnOkWhenMovieNoArgumentsGiven
	@Test
	public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {
		given()
			.when()
				//Está passando o endereço do endpoint para testar
				.get("/movies")
			.then()
				//Verifica o status code da resposta
				.statusCode(200);
	}
	
	@Test
	public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {		
	}
	
	@Test
	public void findByIdShouldReturnMovieWhenIdExists() {		
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {	
	}
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {		
	}
	
	@Test
	public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
	}
	
	@Test
	public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
	}
}
