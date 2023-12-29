package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class SimplePutRequest {

    @Test
    public void putRequest(){
        String baseUrl = "https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/";
        String putBody = """
                 {
                   "name": "Apple Fuji",
                   "price": 9.11
                 }
                """;

        Response response = RestAssured.given().
                contentType("application/json").
                body(putBody).
                put(baseUrl+"31");

        response.prettyPrint();
        response.prettyPeek();

    }
}
