package com.ucodeacademy.day_03_rest_assured_continue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

public class GetRequest {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    }



    @Test
    public void getAllProducts(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/products");

        // verify status code
        //one way with JUnit assertion
        Assert.assertEquals(200, response.getStatusCode());


        // another way to use built-in assertion from Rest Assured
        response.then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);

    }

    @Test
    public void getAllProducts2(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/products");
        // verify/validate status code
        response.then().statusCode(200);

        String productName = response.path("products[1].name");

       System.out.println("Product Name: "+productName);

        int id = response.path("products[0].id");
        System.out.println("ID: "+ id);

        System.out.println("==============================");
        int totalProducts = response.path("products.size()");

        // loop through products and print each product name and ID
        for (int i =0; i < totalProducts; i++ ){

            String eachName = response.path("products["+i+"].name");
            int eachId = response.path("products["+i+"].id");
            System.out.println("Product Name: "+eachName);
            System.out.println("Product ID: "+eachId);

        }

       // response.prettyPrint();
    }
}
