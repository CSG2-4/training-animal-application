package com.example.training_animal_application.dto;

import com.example.training_animal_application.dto.model.AnimalDto;
import lombok.Data;

import java.util.List;

@Data
public class Api1001ResponseDto {
    private List<AnimalDto> animals;
}
