package com.ucodeacademy.day_03_rest_assured_continue;

import com.ucodeacademy.day_02_path_query_param_tokens.GenerateToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SendRequestWithToken {

    public static String token;

    File username = new File("credentials.json");

    File contact = new File("contact.json");

    String loginEndPoint = "https://thinking-tester-contact-list.herokuapp.com/users/login";

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        //token = GenerateToken.loginToGetToken();

    }

    @Test
    public void getAllContacts(){
        String contactId ="";
        // 1. login to get token
        Response postResponse = given().contentType(ContentType.JSON)
                .body(username)
                .when()
                .post(loginEndPoint);
        // verify status code
        postResponse.then().assertThat().statusCode(200);

        // get token from response body and store in token variable
        token = postResponse.path("token");

        System.out.println("==============================");

        // 2. Add new contact
        Response addResponse = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(contact)
                .when()
                .post("/contacts");

        // verify status code
        addResponse.then().assertThat().statusCode(201);

        // get contact id of newly added contact, and store it
        contactId = addResponse.path("_id");
        System.out.println(contactId);

        System.out.println("==================================");

        // 3. get new added contact
        Response contactResponse = given().auth().oauth2(token)
                .when()
                .get("/contacts/"+contactId);

        // verify status code
        contactResponse.then().assertThat().statusCode(200);

        contactResponse.prettyPrint();

        System.out.println("=================================================");

        // 4. Delete new added contact

        Response deleteResponse = given().auth().oauth2(token)
                .accept(ContentType.JSON)
                .when()
                .delete("/contacts/"+contactId);

        // verify status code
        deleteResponse.then().assertThat().statusCode(200);

        deleteResponse.prettyPrint();
    }
}
