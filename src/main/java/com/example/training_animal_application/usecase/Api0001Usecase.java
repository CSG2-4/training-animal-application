package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api0001RequestDto;
import com.example.training_animal_application.dto.Api0001ResponseDto;
import com.example.training_animal_application.dto.model.AnimalNameDto;
import com.example.training_animal_application.dto.model.CageDto;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.model.vo.AnimalWeight;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Api0001Usecase {

    private final CageRepository cageRepository;
    private final HouseRepository houseRepository;
    private final AnimalRepository animalRepository;

    public Api0001ResponseDto usecase(Api0001RequestDto request) {
        List<Cage> cages = cageRepository.fetch();
        List<CageDto> cageDtos = cagesToDtos(cages);

        Api0001ResponseDto response = new Api0001ResponseDto();
        response.setCages(cageDtos);
        return response;
    }

    private List<CageDto> cagesToDtos(List<Cage> cages) {
        List<CageDto> dtos = new ArrayList<>();
        cages.forEach(
                cage -> {
                    List<Animal> animals = getAnimalsInCage(cage.getCageId());
                    CageDto dto = buildCageDto(cage, animals);
                    dtos.add(dto);
                }
        );
        return dtos;
    }

    private CageDto buildCageDto(Cage cage, List<Animal> animals) {
        AnimalWeight totalWeight = new AnimalWeight();
        totalWeight.setValue((int) getTotalWeight(animals));

        Size totalSize = new Size(animals.size());
        List<AnimalNameDto> animalNameDtos = animals.stream().map(
                animal -> {
                    AnimalNameDto dto = new AnimalNameDto();
                    dto.setName(animal.getName());
                    return dto;
                }).toList();

        return toCageDto(cage, totalWeight, totalSize, animalNameDtos);
    }

    private List<Animal> getAnimalsInCage(String cageId) {
        List<House> houses = houseRepository.selectByCageId(cageId);
        return houses.stream().map(house -> animalRepository.find(house.getAnimalId())).toList();
    }

    private double getTotalWeight(List<Animal> animals) {
        return animals.stream().mapToDouble(a -> (double) a.getWeight().getAdjustedWeight()).sum();
    }

    private CageDto toCageDto(Cage cage, AnimalWeight totalWeight, Size totalSize, List<AnimalNameDto> animals) {
        CageDto dto = new CageDto();
        dto.setCageId(cage.getCageId());
        dto.setName(cage.getName());
        dto.setLimitWeight(cage.getLimitWeight().getWeight());
        dto.setLimitSize(cage.getLimitSize().getSize());
        dto.setTotalWeight(totalWeight.getWeight());
        dto.setTotalSize(totalSize.getSize());
        dto.setAnimals(animals);
        return dto;
    }
}