package com.testqa.desafioapi.contract.superhero;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroContractDataProvider;
import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import com.testqa.desafioapi.service.superhero.SuperHeroService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.AssertJUnit.assertTrue;

public class PostSuperHeroContractTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";
    private static final String SUPER_HERO_SCHEMA = "src/test/resources/json_schema/super-heroes/hero_schema.json";

    private SuperHeroDTO superHeroDtoToTest;

    @Autowired
    private SuperHeroService superHeroService;

    @Test(dataProviderClass = SuperHeroContractDataProvider.class, dataProvider = "postSuperHeroContract")
    public void whenPostSuperHeroThenValidateContract(SuperHeroDTO superHeroDTO){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(superHeroDTO)
                .when()
                .post(SUPER_HEROES_URI);
        if(response.getStatusCode() == HttpStatus.SC_CREATED){
            superHeroDtoToTest = response.then().extract().as(SuperHeroDTO.class);
        }
        response.then().body(matchesJsonSchema(new File(SUPER_HERO_SCHEMA)));
    }

    @AfterClass
    public void cleanTestScenario(){
        assertTrue(superHeroService.deleteHero(superHeroDtoToTest));
    }
}
