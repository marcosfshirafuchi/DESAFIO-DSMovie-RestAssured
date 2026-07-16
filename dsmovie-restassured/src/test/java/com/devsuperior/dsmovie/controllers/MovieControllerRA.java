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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MovieControllerRA {

    private String existingMovieTitle, blankMovieTitle;
    private Long existingMovieId, nonExistingMovieId;
    private String adminUsername, adminPassword, clientUsername, clientPassword;
    private String adminToken, clientToken, invalidToken;

    //Criando a request body do post
    private Map<String, Object> postMovieInstance;

    @BeforeEach
    public void setUp() throws JSONException {
        //Endereço que vai estar hospedado o serviço
        baseURI = "http://localhost:8080";
        existingMovieTitle = "Matrix";
        existingMovieId = 7L;
        nonExistingMovieId = 100L;
        blankMovieTitle = "";

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
        //Obtendo o token inválido
        invalidToken = adminToken + "xpto"; // Invalid Token

        //Definindo o postMovieInstance
        postMovieInstance = new HashMap<>();
        postMovieInstance.put("title","Test Movie");
        postMovieInstance.put("score",0.0);
        postMovieInstance.put("count",0);
        postMovieInstance.put("image","https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");

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

    //●	insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle
    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {
        postMovieInstance.put("title",blankMovieTitle);
        //Criar o objeto JSON
        JSONObject newMovie = new JSONObject(postMovieInstance);
        given()
            //Definindo o cabeçalho da requisição do header do método post do endpoint Login
            //Tipo da informação
            .header("Content-type","application/json")
            //Buscando o token de administrador
            .header("Authorization","Bearer " + adminToken)
            .body(newMovie.toString())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            //Está passando o endpoint para testar
            .post("/movies")
        .then()
            //Verificando a resposta da requisição
            .statusCode(422);
    }

    //●	insertShouldReturnForbiddenWhenClientLogged
    @Test
    public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
        //Criar o objeto JSON
        JSONObject newMovie = new JSONObject(postMovieInstance);
        given()
            //Definindo o cabeçalho da requisição do header do método post do endpoint Login
            //Tipo da informação
            .header("Content-type","application/json")
            //Buscando o token de client
            .header("Authorization","Bearer " + clientToken)
            .body(newMovie.toString())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            //Está passando o endpoint para testar
            .post("/movies")
        .then()
            //Verificando a resposta da requisição
            .statusCode(403);
    }

    //●	insertShouldReturnUnauthorizedWhenInvalidToken
    @Test
    public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
        //Criar o objeto JSON
        JSONObject newMovie = new JSONObject(postMovieInstance);
        given()
            //Definindo o cabeçalho da requisição do header do método post do endpoint Login
            //Tipo da informação
            .header("Content-type","application/json")
            //Buscando o token inválido
            .header("Authorization","Bearer " + invalidToken)
            .body(newMovie.toString())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            //Está passando o endpoint para testar
            .post("/movies")
        .then()
            //Verificando a resposta da requisição
            .statusCode(401);
    }
}
