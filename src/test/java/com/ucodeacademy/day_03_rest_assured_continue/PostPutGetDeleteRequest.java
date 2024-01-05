package com.ucodeacademy.day_03_rest_assured_continue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class PostPutGetDeleteRequest {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }

    public int productId;

    @Test
    public void postGetPutPatchDeleteProduct(){

        File jsonFile = new File("product.json");
        // 1. Create a product, and verify/validate it
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonFile)
                .when()
                .post("/products");

        // verify status code
        response.then().assertThat().statusCode(201);
        //response.prettyPrint();
        productId = response.path("id");
        System.out.println(productId);


        // 2. Get newly added product by its ID, and verify/validate
        Response response2 = given().accept(ContentType.JSON)
                .pathParams("id",productId)
                .when()
                .get("/products/{id}");

        // verify status
        response2.then().assertThat().statusCode(200);

        // verify status code with JUnit assertion
        Assert.assertEquals(200, response2.getStatusCode());

        response2.prettyPrint();
        System.out.println("================================");
        File fil2 = new File("product2.json");

        // 3. update newly added product
        Response response3 = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(fil2)
                .when()
                .put("/products/"+productId);

        // verify status code
        response3.then().assertThat().statusCode(200);
        response3.prettyPrint();

        System.out.println("===========================");

        // 4. Partially update newly updated product
        String partialBody = """
                {
                  "price": 1.15
                }
                """;

        Response response4 = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(partialBody)
                .when()
                .patch("/products/"+productId);
        //verify status
        response4.then().assertThat().statusCode(200);

        response4.prettyPrint();

        System.out.println("=============================");

        // 5. Delete product

        Response response5 = given().accept(ContentType.JSON)
                .when()
                .delete("/products/"+productId);

        // Verify status
        response5.then().assertThat().statusCode(200);

        // verify response body success message
        Assert.assertEquals("Deleted",response5.path("success"));
        response5.prettyPrint();
    }


//    @Test
//    public void bgetProductById(){
//        System.out.println(productId);
//        // 2. Get newly added product by its ID, and verify/validate
//        Response response = given().accept(ContentType.JSON)
//                .pathParams("id",productId)
//                .when()
//                .get("/products/{id}");
//
//        // verify status
//        response.then().assertThat().statusCode(200);
//
//        // verify status code with JUnit assertion
//        Assert.assertEquals(200, response.getStatusCode());
//
//        response.prettyPrint();
//    }

}
