package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api0001RequestDto;
import com.example.training_animal_application.dto.Api0001ResponseDto;
import com.example.training_animal_application.dto.model.AnimalNameDto;
import com.example.training_animal_application.dto.model.CageDto;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.repository.CageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Api0001Usecase {

    private final CageRepository cageRepository;

    public Api0001ResponseDto usecase(Api0001RequestDto request) {
        List<Cage> cages = cageRepository.fetch();
        List<CageDto> dtos = cages.stream().map(this::toDto).toList();
        Api0001ResponseDto response = new Api0001ResponseDto();
        response.setCages(dtos);
        return response;
    }

    private CageDto toDto(Cage cage) {
        CageDto dto = new CageDto();
        AnimalWeight totalWeight = new AnimalWeight(getTotalWeight(cage.getAnimals()));
        Size totalSize = new Size(cage.getAnimals().size());
        List<AnimalNameDto> animalNameDtos = cage.getAnimals().stream().map(this::toAnimalNameDto).toList();

        dto.setCageId(cage.getCageId());
        dto.setName(cage.getName());
        dto.setLimitWeight(cage.getLimitWeight().getWeight());
        dto.setLimitSize(cage.getLimitSize().getSize());
        dto.setTotalWeight(totalWeight.getWeight());
        dto.setTotalSize(totalSize.getSize());
        dto.setAnimals(animalNameDtos);

        return dto;
    }

    private AnimalNameDto toAnimalNameDto(Animal animal) {
        AnimalNameDto dto = new AnimalNameDto();
        dto.setName(animal.getName());
        return dto;
    }

    private int getTotalWeight(List<Animal> animals) {
        return animals.stream().mapToInt(a -> a.getWeight().getAdjustedWeight()).sum();
    }
}
