package com.ucodeacademy.day_02_path_query_param_tokens;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GenerateToken {

    static String endpoint = "https://thinking-tester-contact-list.herokuapp.com/users/login";

    static String credentials = """
            {
            "email": "xyz@test.com",
            "password": "testing123"
            }
            """;


   // this method is used to login and generate token
    public static String  loginToGetToken(){
        Response response =  given().contentType(ContentType.JSON)
                .body(credentials)
                .when().post(endpoint);


      String token = response.path("token");

      return token;

    }

    @Test
    public void getAllContacts(){
     String token = loginToGetToken();
       // this is one using header and Authorization
//     given().header("Authorization","Bearer "+ token)

        // this is a simple way
        Response response = given().auth().oauth2(token)
             .when()
             .get("https://thinking-tester-contact-list.herokuapp.com/contacts");

    }

}
