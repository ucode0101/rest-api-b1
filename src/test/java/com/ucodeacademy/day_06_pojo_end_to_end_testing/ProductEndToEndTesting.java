package com.ucodeacademy.day_06_pojo_end_to_end_testing;

import com.ucodeacademy.pojo.ContactResponseBody;
import com.ucodeacademy.pojo.ProductRequestBody;
import com.ucodeacademy.pojo.ProductResponseBody;
import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductEndToEndTesting extends FruitShopUtil {

    // 1 create and object for post request
    ProductRequestBody requestBody = new ProductRequestBody("Watermelon", 6.89 );

    // Create an int to store product id for later use
    int productId =19;

    // 2. add a new product
    @Test
    public void addNewProduct(){
        Response response = given().contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/products");

        // verify status code is 201
       response.then().assertThat().statusCode(201);

       // response.prettyPeek();

        // store product id
        productId = response.jsonPath().getInt("id");

        // create an object of ProductResponseBody and store the response
        ProductResponseBody responseBody = response.as(ProductResponseBody.class);
        //System.out.println(responseBody);

        // verify newly added product name, id, price
        Assert.assertEquals(requestBody.getName(), responseBody.getName());

        Assert.assertTrue(requestBody.getPrice() == responseBody.getPrice());

        Assert.assertTrue(responseBody.getId() > 0);

    }

    // 2. get newly added product by id
    @Test
    public void getProductById(){

        // one way is to follow this way
//        ProductResponseBody responseBody = given().accept(ContentType.JSON)
//                .when().get("/products/"+productId).as(ProductResponseBody.class);
       // System.out.println(responseBody);

        // second way (prefer)
        Response  response = given().contentType(ContentType.JSON)
                .when().get("/products/"+productId);

        //verify status code is 200
        response.then().assertThat().statusCode(200);

        // one way (prefer)
        ProductResponseBody responseBody = response.as(ProductResponseBody.class);

        System.out.println(responseBody);

        // second way
        //ProductResponseBody responseBody2 = response.jsonPath().getObject("",ProductResponseBody.class);
        //System.out.println(responseBody2);

        // verify product name and price
        Assert.assertEquals(requestBody.getName(), responseBody.getName());

        Assert.assertTrue(requestBody.getPrice() == responseBody.getPrice());

    }

    // 3. update newly added product
    @Test
    public void updateProduct(){
        // create object for updating product
        ProductRequestBody updateProduct = new ProductRequestBody("Melon",6.29);

        Response response = given().contentType(ContentType.JSON)
                .body(updateProduct)
                .when()
                .put("/products/"+productId);

        // verify status code is 200
        response.then().assertThat().statusCode(200);

        // create an object and store updated product
        ProductResponseBody responseBody = response.as(ProductResponseBody.class);
        // verify product name and price
        Assert.assertTrue(updateProduct.getPrice() == responseBody.getPrice());

        Assert.assertEquals(updateProduct.getName(), responseBody.getName());
        //response.prettyPrint();

    }

    // 4. partial update product (price)
    @Test
    public void partialUpdateProduct(){
        String body = """
                {
                  "price": 5.79
                }
                """;
        Response response = given().contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/products/"+productId);

        // verify status code is 200
        response.then().assertThat().statusCode(200);

        response.prettyPrint();
    }

    // 5. delete product
    @Test
    public void deleteProduct(){
        Response response = given()
                .when()
                .delete("/products/"+productId);
        // verify status
        response.then().assertThat().statusCode(200);

        // verify success message
        Assert.assertEquals("Deleted",response.jsonPath().getString("success"));
        response.prettyPrint();

        // after deleting, you can get product by id, it should return 404
        response = given().get("/products/"+productId);
        // verify status code is 404 after deleting product
        response.then().assertThat().statusCode(404);
        response.prettyPeek();
    }

    // get all products and store in  list of ProductResponseBody
    @Test
    public void getAllProducts(){
        Response  response =given().contentType(ContentType.JSON)
                .when().get("/products");

        //response.prettyPrint();

        // store all products as list of ProductResponseBody
        List<ProductResponseBody> responseBodyList = response.jsonPath().getList("products",ProductResponseBody.class);
        System.out.println(responseBodyList);

        System.out.println("==================================");

        ProductResponseBody oneProduct = responseBodyList.get(3);
        System.out.println(oneProduct);
    }

}
