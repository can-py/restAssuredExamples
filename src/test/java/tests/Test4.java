package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test4 extends BaseTest {

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void belirliKullanicininGonderileriniGetir() {
        given()
                .queryParam("userId", 1)                 // URL'e ?userId=1 ekler → /posts?userId=1
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))          // Dönen listenin eleman sayısı 0'dan büyük olmalı
                .body("[0].userId", equalTo(1));         // Listenin ilk elemanının userId'si 1 olmalı
    }

    @Test
    public void birdenFazlaQueryParam() {
        given()
                .queryParam("userId", 1)
                .queryParam("id", 3)                     // ?userId=1&id=3 → her ikisi URL'e eklenir
                .when()
                .get("/posts")
                .then()
                .statusCode(200);
    }
}