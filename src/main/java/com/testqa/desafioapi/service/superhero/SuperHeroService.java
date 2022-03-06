package com.testqa.desafioapi.service.superhero;

import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import io.restassured.http.ContentType;
import lombok.NonNull;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.restassured.RestAssured.given;

@Service
public class SuperHeroService {
    private static final String SUPER_HEROES_URI = "/super-heroes";

    public SuperHeroDTO createSuperHero(@NonNull SuperHeroDTO superHeroDTO){
        return given()
                .contentType(ContentType.JSON)
                .body(superHeroDTO)
                .when()
                .post(SUPER_HEROES_URI)
                .then()
                .extract()
                .as(SuperHeroDTO.class);
    }

    public boolean deleteHero(@NonNull SuperHeroDTO superHeroDTO){
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", superHeroDTO.getId())
                .when()
                .delete(SUPER_HEROES_URI + "/{id}")
                .then()
                .extract()
                .response()
                .getStatusCode() == HttpStatus.SC_OK;
    }


    public boolean clearAllSuperHeroes(){
        return deleteListOfSuperHeroes(getAllSuperHeroes());
    }

    public boolean deleteListOfSuperHeroes(@NonNull List<SuperHeroDTO> superHeroDTOList){
        return !superHeroDTOList.isEmpty() && superHeroDTOList.stream().allMatch(this::deleteHero);
    }

    public int superHeroesCount(){
        return getAllSuperHeroes().size();
    }

    public List<SuperHeroDTO> getAllSuperHeroes(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(SUPER_HEROES_URI)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList(".", SuperHeroDTO.class);
    }
}
