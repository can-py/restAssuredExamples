package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test7 extends BaseTest {

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void yeniGonderiOlustur() {

        // Göndereceğimiz JSON body'yi String olarak yazıyoruz
        String body = """
                {
                    "title": "REST Assured öğreniyorum",
                    "body": "Bu benim ilk testim",
                    "userId": 1
                }
                """;
        // Not: """ ... """ Java'daki text block özelliğidir. Çok satırlı String yazmayı kolaylaştırır.

        given()
                .contentType(ContentType.JSON)           // Sunucuya "JSON gönderiyorum" diyoruz (Content-Type: application/json)
                .body(body)                              // Yukarıda yazdığımız String'i body olarak ekle
                .when()
                .post("/posts")                          // POST isteği gönder
                .then()
                .statusCode(201)                         // 201 Created bekliyoruz (kaynak oluşturuldu)
                .body("title", equalTo("REST Assured öğreniyorum"))  // Cevaptaki title doğru mu?
                .body("id", notNullValue());             // Oluşturulan kaydın bir id'si olmalı
    }
}