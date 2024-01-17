package com.ucodeacademy.day_07_final_api_recap;

import com.ucodeacademy.pojo.ProductResponseBody;
import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class SimpleRequests extends FruitShopUtil {


    @Test
    public void getAllProducts(){
        // Get all products and do validation without storing in Response
        given().accept(ContentType.JSON)
                .when().get("/products")
                .then().assertThat().statusCode(200)
                .and()
                .log().body().log().all();


    }

    @Test
    public void getAllProducts2(){
        // get all product and store response in Response and do validation

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/products");

        // verify status code is 200
        // 1 way
        response.then().statusCode(200);

        // another way
        Assert.assertEquals(200, response.getStatusCode());

        // extract any product id
        // 1 way
        int id = response.path("products[2].id");

        // another way
        int id2 = response.jsonPath().getInt("products[3].id");
        System.out.println(id);
        System.out.println(id2);

        // get all products IDs as list
       // 1 way
        List<Integer> allIds = response.path("products.id");
        System.out.println(allIds);

        // get all products IDs as list
        // another way, either create an object of JsonPath, or use jsonPath() chaining
        JsonPath jsonPath = response.jsonPath(); // now the response is stored in jsonPath object

        List<Integer> allIds2 = jsonPath.getList("products.id");

        // or use jsonPath without creating and object
       // List<Integer> allIds2 = response.jsonPath().getList("products.id");
        System.out.println(allIds2);

        System.out.println("===========================");

        // get all products as list of maps using response.path();
        // 1 way
        List<Map<String, Object>> allProducts = response.path("products");
        System.out.println(allProducts);

        // get all products as list of maps using jsonPath object
        List<Map<String, Object>> allProducts2 = jsonPath.getList("products");
        System.out.println(allProducts2);


    }

    @Test
    public void getAllProducts3(){
        // get all products and do validation following POJO approach

        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("/products");

        response.then().statusCode(200);

        // get all products
        List<ProductResponseBody> allProducts = response.jsonPath().getList("products", ProductResponseBody.class);
        System.out.println(allProducts);

        System.out.println("======================================");

        // get one product from allProducts
        ProductResponseBody oneProduct = allProducts.get(2);
        System.out.println(oneProduct);


        System.out.println("=====================================");
        // loop and print each product

        for (ProductResponseBody each : allProducts){
            System.out.println(each);
        }



    }

}
