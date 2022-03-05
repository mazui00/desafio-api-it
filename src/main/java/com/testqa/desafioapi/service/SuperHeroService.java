package com.testqa.desafioapi.service;

import com.testqa.desafioapi.dto.SuperHeroDTO;
import io.restassured.http.ContentType;
import lombok.NonNull;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}
