package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api3001RequestDto;
import com.example.training_animal_application.dto.Api3001ResponseDto;
import com.example.training_animal_application.dto.constant.ResponseStatus;
import com.example.training_animal_application.model.*;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.repository.HouseRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Api3001Usecase {
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
            HousePolicy policy = new PostHousePolicy();
            House house = new House(cage, animal, policy);
            houseRepository.insert(house);

            HousePolicy.ExecuteResult result = house.getResult();
            return switch (result.result) {
                case ACCEPTED -> createSuccessResponse();
                case ACCEPTED_WITH_WARN -> createWarnResponse(result.message);
                case REJECTED -> createErrorResponse("処理中にエラーが発生しました。"); // 念のため記述
            };

        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage());
        } catch (Exception e) {
            log.error("処理中に予期せぬエラーが発生しました。", e);
            return createErrorResponse("処理中にエラーが発生しました。");
        }
    }

    private boolean isValidRequestParam(Api3001RequestDto request) {
        return StringUtils.isNotBlank(request.getAnimalId()) && StringUtils.isNotBlank(request.getCageId());
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
