package com.ucodeacademy.day_05_pojo_api_model;

import com.ucodeacademy.pojo.Products;
import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetResponseBodyInPojo extends FruitShopUtil {

    @Test
    public void getProductByIdInPojoObject(){

        // create an object of product and store product in it directly
        Products product  = given().accept(ContentType.JSON)
                .when()
                .get("/products/15").as(Products.class);

        System.out.println(product);
    }
}
