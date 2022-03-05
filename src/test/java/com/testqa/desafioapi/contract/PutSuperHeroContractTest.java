package com.testqa.desafioapi.contract;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroDataProvider;
import com.testqa.desafioapi.dto.SuperHeroDTO;
import com.testqa.desafioapi.service.SuperHeroService;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.AssertJUnit.assertTrue;

public class PutSuperHeroContractTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";
    private static final String SUPER_HERO_SCHEMA = "src/test/resources/json_schema/super-heroes/hero_schema.json";

    private SuperHeroDTO superHeroDtoToTest;

    @Autowired
    SuperHeroService superHeroService;

    @Test(dataProviderClass = SuperHeroDataProvider.class, dataProvider = "putSuperHeroContract")
    public void whenPutSuperHeroThenValidateSchema(SuperHeroDTO superHeroDtoAdd, SuperHeroDTO superHeroDtoToEdit){
        superHeroDtoToTest = superHeroService.createSuperHero(superHeroDtoAdd);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDtoToTest.getId())
                .body(superHeroDtoToEdit)
                .when()
                .put(SUPER_HEROES_URI + "/{id}")
                .then()
                .body(matchesJsonSchema(new File(SUPER_HERO_SCHEMA)));
    }

    @AfterClass
    public void cleanTestScenario(){
        assertTrue(superHeroService.deleteHero(superHeroDtoToTest));
    }
}
