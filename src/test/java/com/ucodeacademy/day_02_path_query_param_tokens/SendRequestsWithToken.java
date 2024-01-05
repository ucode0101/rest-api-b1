package com.ucodeacademy.day_02_path_query_param_tokens;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SendRequestsWithToken {

    static String token = null;
    @BeforeClass
    public static void setUp(){
        baseURI = "https://thinking-tester-contact-list.herokuapp.com/contacts";
        token = GenerateToken.loginToGetToken();

    }
    String contactId ="65838f383169cf00130e6ac7";


    @Test
    public void getAllContacts(){

        // first way without storing response
        // send get request and do validation
        given().auth().oauth2(token)
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .log().headers();

    }

    @Test
    public void getAllContacts2(){
        // Another way is to send the request,  store response
        // and do validation
        Response response =
                 given().auth().oauth2(token)
                .get(baseURI);

        // verifying status code
        Assert.assertEquals(200, response.statusCode());

        // verifying content type
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());


        // Get random first name from response body (Array JSON)
        String firstName = response.path("[7].firstName");

        //contactId = response.path("[7]._id");
        System.out.println(contactId);

        System.out.println(firstName);

        System.out.println("======================================");

        response.prettyPrint();

    }

    @Test
    public void getContactById1(){
       // System.out.println(contactId);

        given()
                .auth()
                .oauth2(token)
                .when()
                .get(baseURI +"/"+contactId)
                .then()
                //.statusCode(200)
                .log().body();

    }

    @Test
    public void getContactById2(){
        Response response = given()
                .auth().oauth2(token)
                .when()
                .get("/"+contactId);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.contentType().contains("application/json"));

        //response.prettyPrint();
        // print all contact info one by one
       String id  = response.path("_id");
       String firstName = response.path("firstName");
        System.out.println(id);
        System.out.println(firstName);;

        System.out.println("=====================");

        response.prettyPrint();
    }





}
