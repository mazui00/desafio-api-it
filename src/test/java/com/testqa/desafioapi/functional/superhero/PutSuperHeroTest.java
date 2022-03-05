package com.testqa.desafioapi.functional.superhero;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroFunctionalDataProvider;
import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import com.testqa.desafioapi.service.superhero.SuperHeroService;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PutSuperHeroTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";

    private SuperHeroDTO superHeroDtoToTest;
    private List<SuperHeroDTO> superHeroDTOListToTest;

    @Autowired
    SuperHeroService superHeroService;

    @BeforeClass
    public void setup(){
        superHeroDTOListToTest = new ArrayList<>();
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "putSuperHeroValidFunctional")
    public void whenPutSuperHeroThenValidDto(SuperHeroDTO superHeroDtoAdd, SuperHeroDTO superHeroDtoToEdit){
        superHeroDtoToTest = superHeroService.createSuperHero(superHeroDtoAdd);
        SuperHeroDTO superHeroDTO = given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDtoToTest.getId())
                .body(superHeroDtoToEdit)
                .when()
                .put(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(SuperHeroDTO.class);
        assertEquals(superHeroDTO.getId(), superHeroDtoToTest.getId());
        assertThat(superHeroDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(superHeroDtoToEdit);
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "putSuperHeroNotFoundFunctional")
    public void whenPutSuperHeroThenNotFound(SuperHeroDTO superHeroDtoToEdit){
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDtoToEdit.getId())
                .when()
                .put(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "putSuperHeroInValidFunctional")
    public void whenPutSuperHeroThenInvalidRequest(SuperHeroDTO superHeroDtoToAdd, SuperHeroDTO superHeroDtoToEdit, int statusCode){
        SuperHeroDTO superHeroDTO = superHeroService.createSuperHero(superHeroDtoToAdd);
        superHeroDTOListToTest.add(superHeroDTO);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDTO.getId())
                .body(superHeroDtoToEdit)
                .when()
                .put(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(statusCode);
    }

    @AfterClass
    public void cleanTestScenario(){
        assertTrue(superHeroService.deleteListOfSuperHeroes(superHeroDTOListToTest));
        assertTrue(superHeroService.deleteHero(superHeroDtoToTest));
    }

}
