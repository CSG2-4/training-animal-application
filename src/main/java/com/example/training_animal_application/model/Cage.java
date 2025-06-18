package com.example.training_animal_application.model;

import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import lombok.Data;

@Data
public class Cage {
    private String cageId;
    private String name;
    private Weight limitWeight;
    private Size limitSize;
}
