package com.example.training_animal_application.model;

import lombok.Value;

@Value
public class Cage {
    String cageId;
    String name;
    int limitWeight;
    int limitSize;
}
