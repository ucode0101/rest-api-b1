package com.ucodeacademy.day_05_pojo_api_model;

import com.ucodeacademy.pojo.Products;
import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsPOJO extends FruitShopUtil {


    @Test
    public void getProducts(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/products/17");
        // verify status code
        Assert.assertEquals(200, response.getStatusCode());

       // response.prettyPrint();

        Products product = response.as(Products.class);

        System.out.println(product);

        // print product id
        System.out.println(product.getId());
        // print product name
        System.out.println(product.getName());

        // print product price
        System.out.println(product.getPrice());

        // update product name
        product.setName("Orange");
        System.out.println(product);


    }

    @Test
    public void postRequestWithPojo(){
        // Create and object of Product for post request
        Products postBody = new Products();
        postBody.setName("Bana");
        postBody.setPrice(3.44);

        Response response = given().contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .post("/products");

        response.prettyPrint();





    }
}
