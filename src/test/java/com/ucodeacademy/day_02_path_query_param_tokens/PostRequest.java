package com.ucodeacademy.day_02_path_query_param_tokens;

import io.restassured.http.ContentType;
import org.hamcrest.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class PostRequest {
    @BeforeClass
    public static void setUp(){
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }

    String body = """
            {
              "price": 4.70,
              "name": "Blueberry"
              
            }
            """;

    @Test
    public void addNewProduct(){

        given().contentType(ContentType.JSON)
                .body(body)
                .when().post("/products")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", is("Blueberry"));
    }
}
