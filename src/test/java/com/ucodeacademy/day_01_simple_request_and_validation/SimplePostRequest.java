package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class SimplePostRequest {

    @Test
    public void createProduct(){
        String postBody = """
                {
                  "name": "apple super",
                  "price": 11.89
                }
                """;

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(postBody)
                .post("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/");


        Assert.assertEquals("Status does not match", response.statusCode(), 201);

        Assert.assertFalse(response.asPrettyString().isEmpty());
        Assert.assertTrue(!response.asString().isEmpty());

        System.out.println(response.asPrettyString());

    }
}
