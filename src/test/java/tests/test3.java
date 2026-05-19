package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;     // Tüm Hamcrest matchers burada

public class test3 {

    @BeforeAll
    public static void kurulum() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void kullaniciAdiniDogrula() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))         // JSON'daki "name" alanı tam olarak bu değer olmalı
                .body("username", equalTo("Bret"))              // "username" alanı tam olarak "Bret" olmalı
                .body("email", containsString("@"))             // "email" alanı "@" karakteri içermeli
                .body("address.city", equalTo("Gwenborough")); // İç içe alan: address objesi içindeki city
    }
}

// .body("name", equalTo("Ahmet"))                                    // "name" tam olarak "Ahmet" olmalı
// .body("age", equalTo(25))                                          // sayılar için de aynı şekilde
// .body("active", equalTo(true))                                     // boolean için de
// .body("name", not(equalTo("Mehmet")))                              // "name" "Mehmet" olmamalı

// .body("token", notNullValue())                                     // "token" alanı null olmamalı
// .body("deletedAt", nullValue())                                    // "deletedAt" alanı null olmalı

// .body("email", containsString("@"))                                // "@" geçiyor mu
// .body("url", startsWith("https"))                                  // "https" ile başlıyor mu
// .body("filename", endsWith(".pdf"))                                // ".pdf" ile bitiyor mu
// .body("name", containsStringIgnoringCase("ahmet"))                 // büyük/küçük harf fark etmeden içeriyor mu

// .body("age", greaterThan(18))                                      // 18'den büyük mü
// .body("age", greaterThanOrEqualTo(18))                             // 18 veya daha büyük mü
// .body("price", lessThan(100))                                      // 100'den küçük mü
// .body("price", lessThanOrEqualTo(100))                             // 100 veya daha küçük mü

// .body("users", hasSize(10))                                        // "users" listesinde tam 10 eleman var mı
// .body("users", hasSize(greaterThan(0)))                            // liste en az 1 eleman içeriyor mu

// .body("ids", hasItem(5))                                           // listede 5 değeri var mı
// .body("names", hasItem("Ahmet"))                                   // listede "Ahmet" var mı
// .body("ids", hasItems(1, 2, 3))                                   // listede 1, 2 ve 3 hepsi var mı

// .body("ids", everyItem(greaterThan(0)))                            // tüm id'ler 0'dan büyük mü
// .body("emails", everyItem(containsString("@")))                    // tüm email'ler "@" içeriyor mu
// .body("roles", everyItem(notNullValue()))                          // hiçbir rol null değil mi

// .body("age", allOf(greaterThan(18), lessThan(65), notNullValue())) // tüm koşullar sağlanmalı (VE)
// .body("status", anyOf(equalTo("active"), equalTo("pending")))      // en az biri sağlanmalı (VEYA)

// .body("errors", empty())                                           // liste boş olmalı
// .body("errors", not(empty()))                                      // liste boş olmamalı
// .body("name", emptyString())                                       // string boş ("") olmalı
// .body("name", not(emptyString()))                                  // string dolu olmalı