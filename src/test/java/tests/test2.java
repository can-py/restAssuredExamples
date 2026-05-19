package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class test2 {

    @BeforeAll   // Bu metot, sınıftaki tüm testler çalışmadan ÖNCE bir kez çalışır
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";  // Tüm istekler bu URL ile başlar
    }

    @Test
    public void kullaniciyiGetir() {
        given()
                .when()
                .get("/users/1")  // Sadece path yazıyoruz. baseURI + "/users/1" birleşir
                .then()
                .statusCode(200);
    }

    @Test
    public void gonderiGetir() {
        given()
                .when()
                .get("/posts/1")    // baseURI + "/posts/1"
                .then()
                .statusCode(200);
    }
}