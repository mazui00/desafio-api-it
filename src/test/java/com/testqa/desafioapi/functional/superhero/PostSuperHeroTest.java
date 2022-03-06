package com.testqa.desafioapi.functional.superhero;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroFunctionalDataProvider;
import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import com.testqa.desafioapi.service.superhero.SuperHeroService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertTrue;

public class PostSuperHeroTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";

    private SuperHeroDTO superHeroDtoToTest;
    private List<SuperHeroDTO> superHeroDTOListToTest;

    @Autowired
    private SuperHeroService superHeroService;

    @BeforeClass
    public void setup(){
        superHeroDTOListToTest = new ArrayList<>();
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "postSuperHeroValidFunctional")
    public void whenPostSuperHeroThenValidDto(SuperHeroDTO superHeroDTO){
        SuperHeroDTO superHeroResponseDto = given()
                .contentType(ContentType.JSON)
                .body(superHeroDTO)
                .when()
                .post(SUPER_HEROES_URI)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(SuperHeroDTO.class);
        superHeroDtoToTest = superHeroResponseDto;
        assertNotNull(superHeroResponseDto.getId());
        assertThat(superHeroResponseDto).usingRecursiveComparison().ignoringFields("id").isEqualTo(superHeroDTO);
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "postSuperHeroInValidFunctional")
    public void whenPostSuperHeroThenInvalid(SuperHeroDTO superHeroDTO, int statusCode){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(superHeroDTO)
                .when()
                .post(SUPER_HEROES_URI);
        if(response.statusCode() == HttpStatus.SC_CREATED){
            superHeroDTOListToTest.add(response.then().extract().as(SuperHeroDTO.class));
        }
        assertEquals(statusCode, response.statusCode());
    }

    @AfterClass
    public void cleanTestScenario(){
        assertTrue(superHeroService.deleteListOfSuperHeroes(superHeroDTOListToTest));
        assertTrue(superHeroService.deleteHero(superHeroDtoToTest));
    }
}
