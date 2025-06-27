package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api1001RequestDto;
import com.example.training_animal_application.dto.Api1001ResponseDto;
import com.example.training_animal_application.dto.model.AnimalDto;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Api1001Usecase {

    private final AnimalRepository animalRepository;

    public Api1001ResponseDto usecase(Api1001RequestDto request) {
        List<Animal> animals = animalRepository.fetch();
        List<AnimalDto> dtos = animals.stream().map(this::toDto).toList();
        Api1001ResponseDto response = new Api1001ResponseDto();
        response.setAnimals(dtos);
        return response;
    }

    private AnimalDto toDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setAnimalId(animal.getAnimalId());
        dto.setName(animal.getName());
        dto.setWeight(animal.getWeight().getWeight());
        return dto;
    }
}
