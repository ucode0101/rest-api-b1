package com.ucodeacademy.day_04_path_jsonpath_jackson;

import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractDataUsingPath extends FruitShopUtil {


    @Test
    public void getAllProducts(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/products");

        // verify status code
        Assert.assertEquals(200, response.getStatusCode());

        // verify status code with JUni assertion
        response.then().assertThat().statusCode(200);

        // get product name (any product name)
        String productName = response.path("products[1].name");
        System.out.println(productName);

        // get product id (any id)
        int productId = response.path("products[1].id");
        System.out.println(productId);

        // get all product ids as List (Collection)
        List<Integer> allIds = response.path("products.id");
        System.out.println("All products Ids: "+allIds);

        System.out.println("======================================");

        // get all product names as List (Collection)
        List<String> allNames = response.path("products.name");
        System.out.println("all products name: "+allNames);

        System.out.println("===================================");

        // get all products as List of Maps
        List<Map<String, Object>> allProducts = response.path("products");

        // get product id from List of Maps (allProducts)
        int prodId = (int) allProducts.get(2).get("id");

        // get product name List of Maps (allProducts)
        String prodName = (String) allProducts.get(2).get("name");

        System.out.println(prodId);
        System.out.println(prodName);

        System.out.println("============================================");

        System.out.println(allProducts);

        System.out.println("============================================");

       response.prettyPrint();


    }

}
