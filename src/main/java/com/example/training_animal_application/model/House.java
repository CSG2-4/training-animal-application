package com.example.training_animal_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class House {
    private String cageId;
    private String animalId;

    private executeResult result;

    public House(Cage cage, Animal animal, HousePolicy policy) {
        this.cageId = cage.getCageId();
        this.animalId = animal.getAnimalId();

        this.result = policy.execute(cage, animal);
        if (this.result.result == executeResultType.REJECTED) {
            throw new IllegalArgumentException(result.message);
        }
    }

    public enum executeResultType {
        ACCEPTED,
        ACCEPTED_WITH_WARN,
        REJECTED
    }

    @AllArgsConstructor
    public static class executeResult {
        public final executeResultType result;
        public final String message;
    }
}
