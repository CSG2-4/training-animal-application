package com.example.training_animal_application.dto.model;

import lombok.Data;

import java.util.List;

@Data
public class CageDto {
    private String cageId;
    private String name;
    private String limitWeight;
    private String limitSize;
    private String totalWeight;
    private String totalSize;
    private List<AnimalNameDto> animals;
}
