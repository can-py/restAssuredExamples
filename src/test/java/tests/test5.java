package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class test5 {

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void pathParamIleKullaniciyiGetir() {
        int kullaniciId = 2;

        given()
                .pathParam("id", kullaniciId)            // {id} yerine 2 koyar → /users/2
                .when()
                .get("/users/{id}")                      // {id} buradaki placeholder'dır
                .then()
                .statusCode(200)
                .body("id", equalTo(kullaniciId));       // Dönen kullanıcının id'si 2 olmalı
    }
}