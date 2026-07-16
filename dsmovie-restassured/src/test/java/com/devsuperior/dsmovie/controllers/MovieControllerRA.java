package com.devsuperior.dsmovie.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MovieControllerRA {

    private String existingMovieTitle;

    @BeforeEach
    public void setUp() {
        //Endereço que vai estar hospedado o serviço
        baseURI = "http://localhost:8080";
        existingMovieTitle = "Matrix";
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

    //●	findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty
    @Test
    public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {
        given()
           .when()
                //Está passando o endereço do endpoint para testar
                .get("/movies?title={existingMovieTitle}", existingMovieTitle)
           .then()
                //Verifica o status code da resposta
                .statusCode(200)
                //Verifica o id
                .body("content.id[0]", is(4))
                //Verifica o nome
                .body("content.title[0]", equalTo("Matrix Resurrections"))
                //Verifica a imagem
                .body("content.image[0]", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/hv7o3VgfsairBoQFAawgaQ4cR1m.jpg"))
                //Verifica o count
                .body("content.count[0]", is(0))
                //Verifica o score
                .body("content.score[0]", is(0.0F));

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
