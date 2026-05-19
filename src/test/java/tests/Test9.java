package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Test9 extends BaseTest{

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void responsedanVeriCek() {

        Response response = given()                  // Response tipinde bir değişkene atıyoruz
                .when()
                .get("/users/1")
                .andReturn();                            // İsteği gönder ve cevabı döndür

        // Status kodu al
        int statusKodu = response.getStatusCode();
        System.out.println("Status kodu: " + statusKodu);

        // JSON'dan belirli bir alanı oku (JsonPath ile)
        String isim = response.jsonPath().getString("name");      // "name" alanını String olarak al
        int id = response.jsonPath().getInt("id");                // "id" alanını int olarak al
        String sehir = response.jsonPath().getString("address.city"); // İç içe alan

        System.out.println("İsim: " + isim);
        System.out.println("ID: " + id);
        System.out.println("Şehir: " + sehir);

        // JUnit assertion ile doğrulama
        assertEquals(200, statusKodu);
        assertEquals("Leanne Graham", isim);
        assertEquals(1, id);
        assertNotNull(sehir);
    }

    @Test
    public void listedekiIlkElemaniCek() {

        Response response = given()
                .when()
                .get("/users")
                .andReturn();

        // Liste döndüğünde indeksle erişim
        String ilkKullaniciAdi = response.jsonPath().getString("[0].name");    // İlk elemanın name'i
        int listeUzunlugu = response.jsonPath().getList("name").size();       // Tüm name'leri liste olarak al, boyutunu bul

        System.out.println("İlk kullanıcı: " + ilkKullaniciAdi);
        System.out.println("Toplam kullanıcı: " + listeUzunlugu);

        assertTrue(listeUzunlugu > 0);
    }
}