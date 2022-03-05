package com.testqa.desafioapi.dto.superhero;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperHeroDTO {
    private int id;
    private String name;
    private String superName;
    private String profession;
    private int age;
    private boolean canFly;
}
