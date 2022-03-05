package com.testqa.desafioapi.data;

import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroFunctionalDataProvider {

    @DataProvider(name = "getSuperHeroListFunctional")
    public static Object[][] getSuperHeroListFunctional(){
        List<SuperHeroDTO> superHeroDTOList = new ArrayList<>();
        superHeroDTOList.add(
                SuperHeroDTO.builder()
                .name("Gandalf in functional test")
                .superName("you shall not see this")
                .profession("Wizard")
                .age(150)
                .build());
        superHeroDTOList.add(
                SuperHeroDTO.builder()
                        .name("Johnny Blaze")
                        .superName("Ghost Rider")
                        .profession("Stuntman")
                        .canFly(false)
                        .age(50)
                        .build());

        return new Object[][]{{superHeroDTOList}};
    }

    @DataProvider(name = "getSuperHeroByIdFunctional")
    public static Object[][] getSuperHeroByIdFunctional(){
        SuperHeroDTO superHeroDTO = SuperHeroDTO.builder()
                .name("Matt Murdock")
                .superName("Daredevil")
                .profession("Lawyer")
                .canFly(true)
                .age(28)
                .build();
        return new Object[][]{{superHeroDTO}};
    }

    @DataProvider(name = "getSuperHeroByIdInvalidFunctional")
    public static Object[][] getSuperHeroByIdInvalidFunctional(){
        SuperHeroDTO superHeroDTO = SuperHeroDTO.builder()
                .id(9999999)
                .name("Matt Murdock")
                .superName("Daredevil")
                .profession("Lawyer")
                .canFly(true)
                .age(28)
                .build();
        return new Object[][]{{superHeroDTO}};
    }

    @DataProvider(name = "postSuperHeroValidFunctional")
    public static Object[][] postSuperHeroValidFunctional(){
        SuperHeroDTO superHeroDTO = SuperHeroDTO.builder()
                .name("Elektra Natchios")
                .superName("Elektra")
                .profession("Rich")
                .age(25)
                .canFly(false)
                .build();
        return new Object[][]{{superHeroDTO}};
    }

    @DataProvider(name = "postSuperHeroInValidFunctional")
    public static Object[][] postSuperHeroInValidFunctional(){
        SuperHeroDTO emptyFieldSuperHeroDTO = SuperHeroDTO.builder()
                .name("")
                .superName("")
                .profession("")
                .age(0)
                .canFly(false)
                .build();
        SuperHeroDTO nullFieldSuperHeroDTO = SuperHeroDTO.builder()
                .name(null)
                .superName(null)
                .profession(null)
                .age(1)
                .canFly(true)
                .build();
        SuperHeroDTO noFieldsSuperHeroDTO = SuperHeroDTO.builder()
                .build();
        return new Object[][]{
                {emptyFieldSuperHeroDTO, HttpStatus.SC_BAD_REQUEST},
                {nullFieldSuperHeroDTO, HttpStatus.SC_BAD_REQUEST},
                {noFieldsSuperHeroDTO, HttpStatus.SC_BAD_REQUEST}
        };
    }

    @DataProvider(name = "putSuperHeroValidFunctional")
    public static Object[][] putSuperHeroValidFunctional(){
        SuperHeroDTO superHeroDtoToAdd = SuperHeroDTO.builder()
                .name("Oliver Queen")
                .superName("Green Arrow")
                .profession("wealthy playboy")
                .age(24)
                .canFly(false)
                .build();
        SuperHeroDTO superHeroDtoToEdit = SuperHeroDTO.builder()
                .name("Oliver-Queen")
                .superName("Green-Arrow")
                .profession("Wealthy-Playboy")
                .age(20)
                .canFly(true)
                .build();
        return new Object[][]{{superHeroDtoToAdd, superHeroDtoToEdit}};
    }

    @DataProvider(name = "putSuperHeroNotFoundFunctional")
    public static Object[][] putSuperHeroNotFoundFunctional(){
        SuperHeroDTO superHeroDtoToEdit = SuperHeroDTO.builder()
                .id(999999)
                .name("Oliver-Queen1")
                .superName("Green-Arrow")
                .profession("Wealthy-Playboy")
                .age(20)
                .canFly(true)
                .build();
        return new Object[][]{{superHeroDtoToEdit}};
    }


    @DataProvider(name = "putSuperHeroInValidFunctional")
    public static Object[][] putSuperHeroInValidFunctional(){
        SuperHeroDTO superHeroDtoToAdd = SuperHeroDTO.builder()
                .name("Oliver Queen2")
                .superName("Green Arrow")
                .profession("wealthy playboy")
                .age(24)
                .canFly(false)
                .build();
        SuperHeroDTO emptyFieldSuperHeroDTO = SuperHeroDTO.builder()
                .name(" ")
                .superName(" ")
                .profession(" ")
                .age(20)
                .canFly(false)
                .build();
        SuperHeroDTO nullFieldSuperHeroDTO = SuperHeroDTO.builder()
                .name(null)
                .superName(null)
                .profession(null)
                .age(-1)
                .canFly(true)
                .build();
        SuperHeroDTO noFieldsSuperHeroDTO = SuperHeroDTO.builder()
                .build();
        return new Object[][]{
                {emptyFieldSuperHeroDTO, superHeroDtoToAdd, HttpStatus.SC_BAD_REQUEST},
                {nullFieldSuperHeroDTO, superHeroDtoToAdd, HttpStatus.SC_BAD_REQUEST},
                {noFieldsSuperHeroDTO, superHeroDtoToAdd, HttpStatus.SC_BAD_REQUEST}
        };
    }

    @DataProvider(name = "deleteSuperHeroFunctional")
    public static Object[][] deleteSuperHeroFunctional(){
        SuperHeroDTO superVillainDTO = SuperHeroDTO.builder()
                .name("Wilson Grant Fisk")
                .superName("Kingpin")
                .profession("Business man")
                .age(55)
                .canFly(true)
                .build();
        return new Object[][]{{superVillainDTO}};
    }

    @DataProvider(name = "deleteSuperHeroInvalidIdFunctional")
    public static Object[][] deleteSuperHeroInvalidIdFunctional(){
        SuperHeroDTO superVillainDTO = SuperHeroDTO.builder()
                .id(999999)
                .name("Wilson Grant Fisk")
                .superName("Kingpin")
                .profession("Business man")
                .age(55)
                .canFly(true)
                .build();
        return new Object[][]{{superVillainDTO}};
    }
}
