package com.example.training_animal_application.dto;

import com.example.training_animal_application.dto.model.CageDto;
import lombok.Data;

import java.util.List;

@Data
public class Api0001ResponseDto {
    private List<CageDto> cages;
}
