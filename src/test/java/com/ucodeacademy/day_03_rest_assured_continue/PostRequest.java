package com.ucodeacademy.day_03_rest_assured_continue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class PostRequest {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }

    @Test
    public void addNewProductAndDeleteIt(){
        File jsonFile = new File("product.json");

        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonFile)
                .when()
                .post("/products");

        response.prettyPrint();

        int productId = response.path("id");

        // delete newly added product
        Response response1 = given().when().delete("/products/"+productId);

        response1.then().statusCode(200);

        // verify success message in response body
        Assert.assertEquals("Deleted",response1.path("success"));
        response1.prettyPrint();

    }
}
