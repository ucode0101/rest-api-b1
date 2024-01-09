package com.ucodeacademy.day_04_path_jsonpath_jackson;

import com.ucodeacademy.utilities.ContactUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractDataJsonPathWithToken extends ContactUtil {

    String token = generateToken("xyz@test.com","testing123");

    @Test
    public void sendRequestWithToken(){

        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get("/contacts");

        response.then().assertThat().statusCode(200);

        // create JsonPath object
        JsonPath jsonPath = response.jsonPath();

        // get contact id
        String id1 = jsonPath.getString("[0]._id");
        System.out.println(id1);

        System.out.println("=======================");

        // get all contact ids as List
        List<String> allIDs = jsonPath.getList("_id");
        System.out.println("All IDs: "+allIDs);

        System.out.println("=======================");

        // get all first names;
        List<String> firstNames = jsonPath.getList("firstName");
        System.out.println(firstNames);

        System.out.println("=======================");

        // get all last names
        List<String> lastNames = jsonPath.getList("lastName");
        System.out.println(lastNames);

        System.out.println("=======================");

        // get all contact as List of Map
        List<Map<String, Object>> allContacts = jsonPath.getList("");
        System.out.println(allContacts);

        System.out.println("=============================");

        List<Map<String, Object>> all = response.as(List.class);

        System.out.println(all);


        //response.prettyPrint();
    }


}
