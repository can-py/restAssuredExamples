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

public class test6 {

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void istekteHeaderGonder() {
        given()
                .header("Accept", "application/json")       // Sunucuya "JSON formatında cevap istiyorum" diyoruz
                .header("X-Custom-Header", "benim-degerim") // İstediğin isimde özel header ekleyebilirsin
                .when()
                .get("/users/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void cevaptaHeaderDogrula() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json")); // Cevabın Content-Type header'ı JSON olmalı
    }
}