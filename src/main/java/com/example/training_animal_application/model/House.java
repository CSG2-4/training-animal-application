package com.example.training_animal_application.model;

import static com.example.training_animal_application.model.HousePolicy.ExecuteResult;
import lombok.Data;

@Data
public class House {
    private String cageId;
    private String animalId;
    private ExecuteResult result;

    public House(Cage cage, Animal animal, HousePolicy policy) {
        this.cageId = cage.getCageId();
        this.animalId = animal.getAnimalId();

        this.result = policy.execute(cage, animal);
        if (this.result.result == HousePolicy.ExecuteResultType.REJECTED) {
            throw new IllegalArgumentException(result.message);
        }
    }
}