package com.testqa.desafioapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperHeroDTO {
    private int id;
    private String name;
    private String superName;
    private String profession;
    private int age;
    private boolean canFly;
}
