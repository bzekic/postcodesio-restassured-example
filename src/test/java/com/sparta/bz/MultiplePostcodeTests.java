package com.sparta.bz;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MultiplePostcodeTests {

    JsonPath multiplePostcodeResponse;

    @Before
    public void setup(){
        baseURI = "https://api.postcodes.io";
        basePath = "/postcodes/";
//        port = 443;

        JSONObject postcodes = new JSONObject();
        JSONArray multiplePostcodes = new JSONArray();

        multiplePostcodes.add("ub25bj");
        multiplePostcodes.add("ub25aq");

        postcodes.put("postcodes", multiplePostcodes);

        multiplePostcodeResponse =
        given()
                .contentType( ContentType.JSON)
                .body(postcodes)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                ;
    }
    @Test
    public void MultiplePostcodesResponse(){
        Assert.assertEquals( "200", multiplePostcodeResponse.get("status").toString());
    }

    @Test
    public void CheckMultiplePostcodeResponseWithCorrectPostcodes(){
        JSONObject postcodes = new JSONObject();
        JSONArray multiplePostcodes = new JSONArray();

        multiplePostcodes.add("ub25bj");
        multiplePostcodes.add("ub25aq");

        postcodes.put("postcodes", multiplePostcodes);

        given()
                .contentType( ContentType.JSON)
                .body(postcodes)
                .when()
                .post()
                .then()
                .statusCode(200)
                .and()
                .body("result[0].result.postcode", equalTo("UB2 5BJ"))
                .and()
                .body("result[1].result.postcode", equalTo("UB2 5AQ"))
        ;
    }
}
