package com.sparta.bz;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class SimplePostcodeTest {

    @Before
    public void setup(){
        baseURI = "https://api.postcodes.io";
        basePath = "/postcodes/";
//        port = 443;
    }

    @Test
    public void postCodeTestIsSuccessful()
    {
        get("ub25bj")
                .then()
                .statusCode(200)
                .body("result.postcode", equalTo("UB2 5BJ"));
        }
}