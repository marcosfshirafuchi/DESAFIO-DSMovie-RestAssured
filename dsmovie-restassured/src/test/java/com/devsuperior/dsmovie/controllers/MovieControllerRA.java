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
    private Long existingMovieId, nonExistingMovieId;

    @BeforeEach
    public void setUp() {
        //Endereço que vai estar hospedado o serviço
        baseURI = "http://localhost:8080";
        existingMovieTitle = "Matrix";
        existingMovieId = 7L;
        nonExistingMovieId = 100L;
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
    //●	findByIdShouldReturnMovieWhenIdExists
    @Test
    public void findByIdShouldReturnMovieWhenIdExists() {
        given()
            .when()
                //Está passando o endereço do endpoint
                .get("/movies/{id}", existingMovieId)
            .then()
                //Verifica o status code
                .statusCode(200)
                //Verifica o id
                .body("id", is(7))
                //Verifica o nome
                .body("title", equalTo("Titanic"))
                //Verifica a imagem
                .body("image", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/yDI6D5ZQh67YU4r2ms8qcSbAviZ.jpg"))
                //Verifica o count
                .body("count", is(0))
                //Verifica o score
                .body("score", is(0.0F));
    }

    //●	findByIdShouldReturnNotFoundWhenIdDoesNotExist
    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {
        given()
            .when()
                //Está passando o endereço do endpoint
                .get("/movies/{id}", nonExistingMovieId)
            .then()
                //Verifica o status code
                .statusCode(404);
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
