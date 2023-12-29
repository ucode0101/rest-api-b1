package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;


public class SendRequestMethodChaining {


    @Test
    public void requestMethodChaining(){
        String baseUrl = "https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/";

        // Using BaseUri()
//        Response response = RestAssured.given().baseUri(baseUrl)
//                .get();

        Response response = RestAssured.given()
                .when()
                .get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/");

        System.out.println(response.prettyPrint());


    }


    @Test
    public void sendRequestAndValidate(){

        RestAssured.given().when().get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/")
                // part of validation
                .then().statusCode(200)
                .header("Content-Type","application/json");


    }
}
