package com.example.training_animal_application.model;

import java.time.LocalDateTime;

public interface House {
    String getCageId();

    String getAnimalId();

    LocalDateTime getHousedAt();
}
