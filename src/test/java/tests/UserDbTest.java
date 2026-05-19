package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DatabaseUtils;

import java.sql.ResultSet;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserDbTest extends BaseTest {

    @BeforeAll
    static void kurulum() throws Exception {
        DatabaseUtils.baslatDb();
    }

    @AfterAll
    static void kapat() throws Exception {
        DatabaseUtils.baglantiKapat();
    }

    @Test
    @DisplayName("API'den gelen kullanıcı DB'ye doğru kaydedilmeli")
    void apidenGelenKullaniciDbdeDogruKaydedilmeli() throws Exception {

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int    id        = response.jsonPath().getInt("id");
        String email     = response.jsonPath().getString("email");
        String firstName = response.jsonPath().getString("name");
        String lastName  = response.jsonPath().getString("username");

        DatabaseUtils.kullaniciKaydet(id, email, firstName, lastName);

        ResultSet dbSonuc = DatabaseUtils.kullaniciBul(id);

        assertTrue(dbSonuc.next(), "DB'de kullanıcı bulunmalı");
        assertEquals(id,        dbSonuc.getInt("id"));
        assertEquals(email,     dbSonuc.getString("email"));
        assertEquals(firstName, dbSonuc.getString("first_name"));
        assertEquals(lastName,  dbSonuc.getString("last_name"));
    }
}