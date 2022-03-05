package com.testqa.desafioapi;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

@SpringBootTest
@Listeners({ExtentITestListenerClassAdapter.class})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    @Value("${BASE_URI}")
    public String BASE_URI;

    @Value("${BASE_PATH}")
    public String BASE_PATH;

    @BeforeClass(alwaysRun = true)
    public void init(){
        baseURI = BASE_URI;
        basePath = BASE_PATH;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
