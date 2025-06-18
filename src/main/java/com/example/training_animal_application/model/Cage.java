package com.example.training_animal_application.model;

import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import lombok.Value;

@Value
public class Cage {
    String cageId;
    String name;
    Weight limitWeight;
    Size limitSize;
}
