package com.testqa.desafioapi.data;

import com.testqa.desafioapi.dto.superhero.SuperHeroDTO;
import org.testng.annotations.DataProvider;

public class SuperHeroContractDataProvider {

    @DataProvider(name = "getSuperHeroByIdContract")
    public static Object[][] getSuperHeroByIdContract(){
        SuperHeroDTO superHeroDTO = SuperHeroDTO.builder()
                .name("Gandalf")
                .superName("you shall not see this")
                .profession("Wizard")
                .age(200)
                .canFly(true)
                .build();
        return new Object[][]{{superHeroDTO}};
    }

    @DataProvider(name = "postSuperHeroContract")
    public static Object[][] postSuperHeroContract(){
        SuperHeroDTO superHeroDTO = SuperHeroDTO.builder()
                .name("Bruce Wayne")
                .superName("Batman")
                .profession("businessman, entrepreneur and philanthropist")
                .age(50)
                .canFly(false)
                .build();
        return new Object[][]{{superHeroDTO}};
    }

    @DataProvider(name = "putSuperHeroContract")
    public static Object[][] putSuperHeroContract(){
        SuperHeroDTO superHeroDtoToAdd = SuperHeroDTO.builder()
                .name("Bruce Wayne clone")
                .superName("Batman clone")
                .profession("businessman, entrepreneur and philanthropist clone")
                .age(50)
                .canFly(false)
                .build();
        SuperHeroDTO superHeroDtoToEdit = SuperHeroDTO.builder()
                .name("T’Challa")
                .superName("Black Panther")
                .profession("King of Wakanda")
                .canFly(true)
                .age(35)
                .build();
        return new Object[][]{{superHeroDtoToAdd, superHeroDtoToEdit}};
    }


}
