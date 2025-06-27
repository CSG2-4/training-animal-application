package com.example.training_animal_application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HouseEntity {
    private String cageId;
    private String animalId;
    private LocalDateTime housedAt;
}
