package com.testqa.desafioapi.functional.superhero;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroFunctionalDataProvider;
import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import com.testqa.desafioapi.service.superhero.SuperHeroService;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.blankOrNullString;

public class DeleteSuperHeroTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";

    private SuperHeroDTO superHeroDtoToTest;
    private List<SuperHeroDTO> superHeroDTOListToTest;

    @Autowired
    private SuperHeroService superHeroService;

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "deleteSuperHeroFunctional")
    public void whenDeleteSuperHeroThenNoContent(SuperHeroDTO superHeroDTO){
        superHeroDtoToTest = superHeroService.createSuperHero(superHeroDTO);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDtoToTest.getId())
                .when()
                .delete(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .body(is(blankOrNullString()));
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "deleteSuperHeroInvalidIdFunctional")
    public void whenDeleteSuperHeroThenNoFound(SuperHeroDTO superHeroDTO){
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDTO.getId())
                .when()
                .delete(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
