package com.example.training_animal_application.model;

import com.example.training_animal_application.model.error.ErrorException;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddHouse implements House {
    private final Cage cage;
    private final Animal animal;
    private final LocalDateTime housedAt;
    private String message = null;

    public AddHouse(Cage cage, Animal animal) throws ErrorException {
        this.cage = cage;
        this.animal = animal;
        this.housedAt = LocalDateTime.now();

        if (isOverTrueWeight()) {
            throw new ErrorException("制限重量を超えています。");
        }
        if (isOverTrueSize()) {
            throw new ErrorException("制限収容数を超えています。");
        }
        if (isOverWeight() && isOverSize()) {
            throw new ErrorException("制限重量・制限収容数を超えています。");
        }

        if (isOverWeight()) {
            Weight diff = getLimitWeightDiff();
            cage.getAnimals().add(animal);
            this.message = "制限重量を" + diff.getWeight() + "超えました。";
            return;
        }

        if (isOverSize()) {
            cage.getAnimals().add(animal);
            this.message = "制限収容数を超えました。";
            return;
        }

        cage.getAnimals().add(animal);
    }

    private boolean isOverWeight() {
        Weight weightDiff = getLimitWeightDiff();
        if (0 < weightDiff.getValue()) {
            return true;
        }
        return false;
    }

    private boolean isOverTrueWeight() {
        Weight trueWeightDiff = getLimitTrueWeightDiff();
        if (0 < trueWeightDiff.getValue()) {
            return true;
        }
        return false;
    }

    private Weight getTrueLimitWeight() {
        return new Weight((int) (cage.getLimitWeight().getValue() * 1.1));
    }

    private Weight getLimitWeightDiff() {
        Weight addedWeight = cage.getTotalWeight().increase(animal.getWeight().getHousedWeight());
        return addedWeight.reduce(cage.getLimitWeight());
    }

    private Weight getLimitTrueWeightDiff() {
        Weight addedWeight = cage.getTotalWeight().increase(animal.getWeight().getHousedWeight());
        return addedWeight.reduce(getTrueLimitWeight());
    }

    private boolean isOverSize() {
        if (cage.getLimitSize().equals(cage.getTotalSize())) {
            return true;
        }
        return false;
    }

    private boolean isOverTrueSize() {
        if (getTrueLimitSize().equals(cage.getTotalSize())) {
            return true;
        }
        return false;
    }

    private Size getTrueLimitSize() {
        return new Size(cage.getLimitSize().getValue() + 1);
    }

    @Override
    public String getCageId() {
        return cage.getCageId();
    }

    @Override
    public String getAnimalId() {
        return animal.getAnimalId();
    }
}
