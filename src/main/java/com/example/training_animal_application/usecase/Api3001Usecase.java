package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api1001RequestDto;
import com.example.training_animal_application.dto.Api1001ResponseDto;
import com.example.training_animal_application.dto.Api3001RequestDto;
import com.example.training_animal_application.dto.Api3001ResponseDto;
import com.example.training_animal_application.dto.model.AnimalDto;
import com.example.training_animal_application.model.AddHouse;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.model.error.ErrorException;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Api3001Usecase {

    private final CageRepository cageRepository;
    private final AnimalRepository animalRepository;
    private final HouseRepository houseRepository;

    public Api3001ResponseDto usecase(Api3001RequestDto request) {
        Cage cage = cageRepository.findByCageId(request.getCageId());
        if (cage == null) {
            return new Api3001ResponseDto("ERROR", "存在しないcageId");
        }

        Animal animal = animalRepository.fetchByAnimalId(request.getAnimalId());
        if (animal == null) {
            return new Api3001ResponseDto("ERROR", "存在しないanimalId");
        }

        AddHouse house;
        try {
            house = new AddHouse(cage, animal);
        } catch (ErrorException e) {
            return new Api3001ResponseDto("ERROR", e.getMessage());
        }

        houseRepository.insert(house);

        if (house.getMessage() != null) {
            return new Api3001ResponseDto("WARN", house.getMessage());
        }

        return new Api3001ResponseDto("OK", null);
    }

    private AnimalDto toDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setAnimalId(animal.getAnimalId());
        dto.setName(animal.getName());
        dto.setWeight(animal.getWeight().getWeight());
        return dto;
    }
}
