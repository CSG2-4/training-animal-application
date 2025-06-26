package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api3001RequestDto;
import com.example.training_animal_application.dto.Api3001ResponseDto;
import com.example.training_animal_application.dto.constant.ResponseStatus;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.repository.HouseRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Api3001Usecase {
    // 最大重量の10%
    private final double MAX_WEIGHT_RULE = 1.1;
    // 最大収容数+1匹
    private final int MAX_COUNT_RULE = 1;
    private final CageRepository cageRepository;
    private final AnimalRepository animalRepository;
    private final HouseRepository houseRepository;

    public Api3001ResponseDto usecase(Api3001RequestDto request) {
        if (!isValidRequestParam(request)) {
            return createErrorResponse("リクエストパラメータが不正です。");
        }

        Cage cage = cageRepository.find(request.getCageId());
        Animal animal = animalRepository.find(request.getAnimalId());

        if (cage == null || animal == null) {
            return createErrorResponse("檻または動物が存在しません。");
        }

        try {
            List<Animal> currentAnimals = getAnimalsInCage(cage.getCageId());

            House.HousingResult result = House.evaluateHousing(
                    cage, animal, currentAnimals
            );

            return switch (result.result) {
                case ACCEPTED -> {
                    addAnimalToCage(cage, animal);
                    yield createSuccessResponse();
                }
                case ACCEPTED_WITH_WARN -> {
                    addAnimalToCage(cage, animal);
                    yield createWarnResponse(result.message);
                }
                case REJECTED -> createErrorResponse(result.message);
            };

        } catch (Exception e) {
            log.error("処理中に予期せぬエラーが発生しました。", e);
            return createErrorResponse("処理中にエラーが発生しました。");
        }
    }

    private boolean isValidRequestParam(Api3001RequestDto request) {
        return StringUtils.isNotBlank(request.getAnimalId()) && StringUtils.isNotBlank(request.getCageId());
    }

    private List<Animal> getAnimalsInCage(String cageId) {
        List<House> houses = houseRepository.fetchBy(cageId);
        return houses.stream().map(house -> animalRepository.find(house.getAnimalId())).toList();
    }

    private void addAnimalToCage(Cage cage, Animal animal) {
        House house = new House();
        house.setCageId(cage.getCageId());
        house.setAnimalId(animal.getAnimalId());
        houseRepository.insert(house);
    }

    private Api3001ResponseDto createSuccessResponse() {
        Api3001ResponseDto response = new Api3001ResponseDto();
        response.setResult(ResponseStatus.OK.name());
        return response;
    }

    private Api3001ResponseDto createWarnResponse(String message) {
        Api3001ResponseDto response = new Api3001ResponseDto();
        response.setResult(ResponseStatus.WARN.name());
        response.setMessage(message);
        return response;
    }

    private Api3001ResponseDto createErrorResponse(String message) {
        Api3001ResponseDto response = new Api3001ResponseDto();
        response.setResult(ResponseStatus.ERROR.name());
        response.setMessage(message);
        return response;
    }
}
