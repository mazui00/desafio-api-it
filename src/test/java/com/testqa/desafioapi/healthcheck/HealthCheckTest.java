package com.testqa.desafioapi.healthcheck;

import com.testqa.desafioapi.BaseTest;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.when;

public class HealthCheckTest extends BaseTest {
    @Value("${HEALTH_CHECK_URI}")
    private String HEALTH_CHECK_URI;

    @Test
    public void whenHealthCheckThenIsUp(){
        when()
                .get(HEALTH_CHECK_URI).prettyPeek()
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }
}
