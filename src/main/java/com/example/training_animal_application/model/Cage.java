package com.example.training_animal_application.model;

import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cage {
    private String cageId;
    private String name;
    private Weight limitWeight;
    private Size limitSize;
    private List<Animal> animals = new ArrayList<>();

    public Weight getTotalWeight() {
        return animals.stream()
                .map(Animal::getWeight)
                .map(AnimalWeight::getHousedWeight)
                .reduce(new Weight(0), Weight::increase);
    }

    public Size getTotalSize() {
        return new Size(animals.size());
    }
}
