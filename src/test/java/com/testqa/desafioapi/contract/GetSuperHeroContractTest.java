package com.testqa.desafioapi.contract;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroDataProvider;
import com.testqa.desafioapi.dto.SuperHeroDTO;
import com.testqa.desafioapi.service.SuperHeroService;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class GetSuperHeroContractTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";
    private static final String SUPER_HEROES_LIST_SCHEMA = "src/test/resources/json_schema/super-heroes/heroes_list_schema.json";
    private static final String SUPER_HERO_SCHEMA = "src/test/resources/json_schema/super-heroes/hero_schema.json";

    private SuperHeroDTO superHeroDtoToTest;

    @Autowired
    private SuperHeroService superHeroService;

    @Test(dataProvider = "getSuperHeroByIdContract", dataProviderClass = SuperHeroDataProvider.class, priority = 1)
    public void whenGetSuperHeroByIdThenValidateSchema(SuperHeroDTO superHeroDTO){
        superHeroDtoToTest = superHeroService.createSuperHero(superHeroDTO);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDtoToTest.getId())
                .when()
                .get(SUPER_HEROES_URI + "/{id}")
                .then()
                .body(matchesJsonSchema(new File(SUPER_HERO_SCHEMA)));
    }

    @Test(priority = 2)
    public void whenGetSuperHeroesThenValidateSchema(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(SUPER_HEROES_URI)
                .then()
                .body(matchesJsonSchema(new File(SUPER_HEROES_LIST_SCHEMA)));
    }

    @AfterClass
    public void cleanTestScenario(){
        assertTrue(superHeroService.deleteHero(superHeroDtoToTest));
    }
}
