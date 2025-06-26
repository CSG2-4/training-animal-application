package com.example.training_animal_application.model;

import lombok.Data;

import java.util.List;

@Data
public class House {
    // 例外ルール(最大重量)：最大重量の10%
    private static final double MAX_WEIGHT_RULE = 1.1;
    // 例外ルール(最大収容数)：最大収容数+1匹
    private static final int MAX_COUNT_RULE = 1;
    private String cageId;
    private String animalId;

    public enum HousingResultType {
        ACCEPTED,
        ACCEPTED_WITH_WARN,
        REJECTED
    }

    public static class HousingResult {
        public final HousingResultType result;
        public final String message;

        public HousingResult(HousingResultType result, String message) {
            this.result = result;
            this.message = message;
        }
    }

    public static HousingResult evaluateHousing(
            Cage cage,
            Animal animal,
            List<Animal> currentAnimals
    ) {
        double totalWeight = currentAnimals.stream().mapToDouble(a -> a.getWeight().getAdjustedWeight()).sum();

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

        if (!overWeight && !overCount) {
            return new HousingResult(HousingResultType.ACCEPTED, null);
        }

        if (overWeight && overCount) {
            return new HousingResult(
                    HousingResultType.REJECTED, "最大重量および最大収容数の両方を超えているため収容できません。"
            );
        }

        if (overWeight && withinWeightMargin && !overCount) {
            double overBy = newTotalWeight - maxWeight;
            return new HousingResult(
                    HousingResultType.ACCEPTED_WITH_WARN, String.format("最大重量が%.1fKgオーバーしました。", overBy)
            );
        }

        if (overCount && withinCountMargin && !overWeight) {
            int overBy = newTotalCount - maxCount;
            return new HousingResult(
                    HousingResultType.ACCEPTED_WITH_WARN, String.format("最大収容数を%d匹超えています。", overBy)
            );
        }

        return new HousingResult(HousingResultType.REJECTED, "収容条件を満たしていません。");
    }
}
