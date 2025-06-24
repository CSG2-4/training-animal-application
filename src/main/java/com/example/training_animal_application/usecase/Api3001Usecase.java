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
        try {
            if (!isValidRequestParam(request)) {
                log.info("バリデーションエラー: リクエストパラメータが不正です。");
                return createErrorResponse("リクエストパラメータが不正です。");
            }

            // 対象の檻と動物を取得
            Cage cage = cageRepository.find(request.getCageId());
            Animal animal = animalRepository.find(request.getAnimalId());

            if (cage == null || animal == null) {
                return createErrorResponse("檻または動物が存在しません。");
            }

            // 現在収容されている動物,総重量,収容数を取得
            List<Animal> currentAnimals = getAnimalsInCage(cage.getCageId());
            double totalWeight = getTotalWeight(currentAnimals);
            int totalCount = currentAnimals.size();

            double adjustedWeight = animal.getWeight().getAdjustedWeight();
            double newTotalWeight = totalWeight + adjustedWeight;
            int newTotalCount = totalCount + 1;

            double maxWeight = cage.getLimitWeight().getValue();
            int maxCount = cage.getLimitSize().getValue();

            boolean overWeight = newTotalWeight > maxWeight;
            boolean overCount = newTotalCount > maxCount;
            boolean withinWeightMargin = newTotalWeight <= maxWeight * MAX_WEIGHT_RULE;
            boolean withinCountMargin = newTotalCount <= maxCount + MAX_COUNT_RULE;

            // 収容成功
            if (!overWeight && !overCount) {
                addAnimalToCage(cage, animal);
                return createSuccessResponse();
            }

            // 両方超過 → エラー
            if (overWeight && overCount) {
                return createErrorResponse("最大重量および最大収容数の両方を超えているため収容できません。");
            }

            // 重量のみ許容範囲内での超過（+10%以内）
            if (overWeight && withinWeightMargin && !overCount) {
                addAnimalToCage(cage, animal);
                double overBy = newTotalWeight - maxWeight;
                return createWarnResponse(String.format("最大重量が%.1fKgオーバーしました。", overBy));
            }

            // 収容数のみ許容範囲内での超過（+1匹まで）
            if (overCount && withinCountMargin && !overWeight) {
                addAnimalToCage(cage, animal);
                int overBy = newTotalCount - maxCount;
                return createWarnResponse(String.format("最大収容数を%d匹超えています。", overBy));
            }

            return createErrorResponse("収容条件を満たしていません。");

        } catch (Exception e) {
            log.error("処理中に予期せぬエラーが発生しました。", e);
            return createErrorResponse("処理中にエラーが発生しました。");
        }
    }

    private boolean isValidRequestParam(Api3001RequestDto request) {
        return StringUtils.isNotBlank(request.getAnimalId()) && StringUtils.isNotBlank(request.getCageId());
    }

    private List<Animal> getAnimalsInCage(String cageId) {
        List<House> houses = houseRepository.selectByCageId(cageId);
        return houses.stream().map(house -> animalRepository.find(house.getAnimalId())).toList();
    }

    private double getTotalWeight(List<Animal> animals) {
        return animals.stream().mapToDouble(a -> (double) a.getWeight().getAdjustedWeight()).sum();
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
