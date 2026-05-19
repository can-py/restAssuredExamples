package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DatabaseUtils;

import java.sql.ResultSet;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class UserDbTest {

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

        // ADIM 1: jsonplaceholder'dan 2 id'li kullanıcıyı çek
        // jsonplaceholder.typicode.com → tamamen ücretsiz, kayıt gerektirmez
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // ADIM 2: API cevabından değerleri oku
        // jsonplaceholder cevabı: {"id":2, "name":"...", "email":"...", "username":"..."}
        // reqres.in'den farklı olarak "data" katmanı yok, direkt üst seviyede
        int    id        = response.jsonPath().getInt("id");
        String email     = response.jsonPath().getString("email");
        String firstName = response.jsonPath().getString("name");   // jsonplaceholder'da first_name yok, "name" var
        String lastName  = response.jsonPath().getString("username"); // lastName yerine username kullanıyoruz

        // ADIM 3: okunan değerleri DB'ye kaydet
        DatabaseUtils.kullaniciKaydet(id, email, firstName, lastName);

        // ADIM 4: DB'den aynı kullanıcıyı geri oku
        ResultSet dbSonuc = DatabaseUtils.kullaniciBul(id);

        // ADIM 5: DB'de kayıt var mı?
        assertTrue(dbSonuc.next(), "DB'de kullanıcı bulunmalı");

        // ADIM 6: API'den gelen ile DB'deki eşleşiyor mu?
        assertEquals(id,        dbSonuc.getInt("id"));
        assertEquals(email,     dbSonuc.getString("email"));
        assertEquals(firstName, dbSonuc.getString("first_name"));
        assertEquals(lastName,  dbSonuc.getString("last_name"));
    }
}