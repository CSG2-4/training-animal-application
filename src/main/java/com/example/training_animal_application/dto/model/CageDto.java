package com.example.training_animal_application.dto.model;

import lombok.Data;

@Data
public class CageDto {
    private String cageId;
    private String name;
    private int limitWeight;
    private int limitSize;
}
