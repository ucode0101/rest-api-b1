package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class SimpleGetRequest {

    @Test
    public void getAllProducts(){

        // we can directly call the get request
        //RestAssured.get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/");

        // Send get request and store response in response including response body, status, header etc...
        // All the information is stored in response
        Response response = RestAssured.get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/");

        // Printing status code using statusCode()
        System.out.println("Status code is: "+ response.statusCode());

        // Printing status code using getStatusCode()
        System.out.println("Status code is: "+response.getStatusCode());

        //
        System.out.println(response.statusLine());

        // get the headers of the response
        System.out.println("Header of the response: "+ response.getHeaders());

        // get the Content-Type
        System.out.println("Content-Type: "+ response.getContentType());

        // get/return the value for the given key under the Headers
        System.out.println("Date: "+ response.getHeader("Date"));
        System.out.println("Content-Type: "+ response.getHeader("Content-Type"));

        // get/return content Type
        System.out.println("Content-Type: "+ response.contentType());



    }

}
