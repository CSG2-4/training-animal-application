package com.example.training_animal_application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CageEntity {
    private String cageId;
    private String name;
    private int limitWeight;
    private int limitSize;
}
