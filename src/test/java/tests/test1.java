package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class test1 {

    @Test
    public void statusKoduDogrulamasiYap() {

        given()                                      // İsteği hazırlama aşaması. Şu an ekleyecek bir şeyimiz yok.
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")  // GET isteği bu URL'e gider
                .then()
                .statusCode(200);                    // Gelen cevabın HTTP durum kodu 200 olmalı
    }
}