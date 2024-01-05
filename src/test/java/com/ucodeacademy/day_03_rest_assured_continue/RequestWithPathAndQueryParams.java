package com.ucodeacademy.day_03_rest_assured_continue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RequestWithPathAndQueryParams {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }

    @Test
    public void getProductPathParameters(){

        Response response = given().accept(ContentType.JSON)
                .pathParams("id",11)
                .when()
                .get("/products/{id}");

        response.prettyPrint();
    }

    @Test
    public void getProductQueryParameters(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("limit",20)
                .queryParam("search", "a")
                .when()
                .get("/products");
        response.prettyPrint();
    }
}
