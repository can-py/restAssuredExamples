package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test8 extends BaseTest{

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void gonderiGuncelle() {                  // PUT: Kaydı tamamen güncelle
        String guncellenmisBody = """
                {
                    "id": 1,
                    "title": "Güncellenmiş başlık",
                    "body": "Güncellenmiş içerik",
                    "userId": 1
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(guncellenmisBody)
                .when()
                .put("/posts/1")                         // 1 id'li gönderiyi güncelle
                .then()
                .statusCode(200)
                .body("title", equalTo("Güncellenmiş başlık"));
    }

    @Test
    public void gonderiSil() {                       // DELETE: Kaydı sil
        given()
                .when()
                .delete("/posts/1")                      // 1 id'li gönderiyi sil
                .then()
                .statusCode(200);                        // Silme başarılı
    }

    @Test
    public void gonderiKismenGuncelle() {            // PATCH: Sadece belirli alanları güncelle
        String kismiGuncelleme = """
                {
                    "title": "Sadece başlık değişti"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(kismiGuncelleme)
                .when()
                .patch("/posts/1")                       // PATCH ile sadece gönderilen alanlar güncellenir
                .then()
                .statusCode(200)
                .body("title", equalTo("Sadece başlık değişti"));
    }
}