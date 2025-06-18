package com.example.training_animal_application.dto.model;

import lombok.Data;

@Data
public class CageDto {
    private String cageId;
    private String name;
    private String limitWeight;
    private String limitSize;
}
