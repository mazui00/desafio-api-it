package com.testqa.desafioapi.functional.superhero;

import com.testqa.desafioapi.BaseTest;
import com.testqa.desafioapi.data.SuperHeroFunctionalDataProvider;
import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import com.testqa.desafioapi.service.superhero.SuperHeroService;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.hasItems;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


@Slf4j
public class GetSuperHeroTest extends BaseTest {
    private static final String SUPER_HEROES_URI = "/super-heroes";

    private SuperHeroDTO superHeroDtoToTest;
    private List<SuperHeroDTO> superHeroDTOListToTest;

    @Autowired
    SuperHeroService superHeroService;

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "getSuperHeroListFunctional")
    public void whenGetSuperHeroesThenSuperHeroesList(List<SuperHeroDTO> superHeroDTOList){
        superHeroDTOListToTest = superHeroDTOList.stream()
                .map(superHeroDTO -> superHeroService.createSuperHero(superHeroDTO))
                .collect(Collectors.toList());
        List<SuperHeroDTO> superHeroDTOResponseList = given()
                .contentType(ContentType.JSON)
                .when()
                .get(SUPER_HEROES_URI).prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList(".", SuperHeroDTO.class);
        assertTrue(superHeroService.superHeroesCount() >= superHeroDTOList.size());
        assertThat(superHeroDTOResponseList, hasItems(superHeroDTOListToTest.get(0), superHeroDTOListToTest.get(1)));
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "getSuperHeroByIdFunctional")
    public void whenGetSuperHeroByIdThenValidDto(SuperHeroDTO superHeroDTO){
        superHeroDtoToTest = superHeroService.createSuperHero(superHeroDTO);
        SuperHeroDTO superHeroResponseDto = given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDtoToTest.getId())
                .when()
                .get(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(SuperHeroDTO.class);
        assertEquals(superHeroDtoToTest, superHeroResponseDto);
    }

    @Test(dataProviderClass = SuperHeroFunctionalDataProvider.class, dataProvider = "getSuperHeroByIdInvalidFunctional")
    public void whenGetSuperHeroByIdThenNotFound(SuperHeroDTO superHeroDTO){
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDTO.getId())
                .when()
                .get(SUPER_HEROES_URI + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @AfterClass
    public void cleanTestScenario(){
        assertTrue(superHeroService.deleteListOfSuperHeroes(superHeroDTOListToTest));
        assertTrue(superHeroService.deleteHero(superHeroDtoToTest));
    }


}
