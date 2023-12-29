package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class GetRequestAndValidate {

    @Test
    public void getRequestAndValidation(){

        // send get request and store response
        Response response = RestAssured.given().get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products/");

        // Verify that status code is 200
        Assert.assertEquals("Status code does not match",response.statusCode(), 200);

        // Verify Content Type
        Assert.assertEquals("Content type does not match",response.contentType(),"application/json");

        //get response body asString()
       String responseBody = response.asString();
        System.out.println(responseBody);

        System.out.println("==========================================");

        // get response body asPrettyString()
        String pretty = response.asPrettyString();
        System.out.println(pretty);

        System.out.println("==============================");

        // get body().asString()
        String strBody = response.body().asString();
        System.out.println(strBody);

        System.out.println("===============================");

        //get body().asPrettyString()
        String strPrettyBody = response.body().asPrettyString();
        System.out.println(strPrettyBody);

        System.out.println("===================================");
        // get prettyPrint()
        String s = response.prettyPrint();
        System.out.println("Pretty Print: "+ s);

    }
}
