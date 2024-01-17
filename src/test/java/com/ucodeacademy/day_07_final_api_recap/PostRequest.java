package com.ucodeacademy.day_07_final_api_recap;

import com.ucodeacademy.pojo.ProductRequestBody;
import com.ucodeacademy.pojo.ProductResponseBody;
import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class PostRequest extends FruitShopUtil {

    String newProduct = """
             {
               "name": "Banana",
               "price": 1.79
             }
            """;

    @Test
    public void addProduct(){
        // add a new products taking request body from String
        Response response = given().contentType(ContentType.JSON)
                .body(newProduct)
                .when()
                .post("/products");

        // verify status code is 201
        response.then().assertThat().statusCode(201);

        // verify that response contains newProduct
        String productName = response.jsonPath().getString("name");


        Assert.assertTrue(newProduct.contains(productName));
        System.out.println(productName);

    }

    @Test
    public void addProduct2(){
        // add a new product taking request body from product.json file
        File body = new File("product.json");


        Response response = given().contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/products");
        //verify status code
        response.then().assertThat().statusCode(201);

        response.prettyPrint();

    }

    @Test
    public void addProduct3(){
        // add a new product with POJO
        ProductRequestBody body = new ProductRequestBody("Grapes",3.44);
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post("/products");
        response.then().assertThat().statusCode(201);

        // do assertion
        Assert.assertEquals(body.getName(), response.jsonPath().getString("name"));
        Assert.assertTrue(body.getPrice() == response.jsonPath().getDouble("price"));

        // store response body in POJO
        ProductResponseBody responseBody = response.as(ProductResponseBody.class);
        System.out.println(responseBody);

        System.out.println("==============================");
        response.prettyPrint();
    }
}
