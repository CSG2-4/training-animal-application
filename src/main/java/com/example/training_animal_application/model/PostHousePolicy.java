package com.example.training_animal_application.model;

import lombok.Data;

@Data
public class PostHousePolicy implements HousePolicy {
    private static final double MAX_WEIGHT_RULE = 1.1;
    private static final int MAX_COUNT_RULE = 1;

    @Override
    public ExecuteResult execute(Cage cage, Animal animal) {
        double totalWeight = cage.getAnimals().stream().mapToDouble(a -> a.getWeight().getAdjustedWeight()).sum();
        int totalCount = cage.getAnimals().size();

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
            return new ExecuteResult(ExecuteResultType.ACCEPTED, null);
        }

        if (overWeight && overCount) {
            return new ExecuteResult(ExecuteResultType.REJECTED,
                    "最大重量および最大収容数の両方を超えているため収容できません。");
        }

        if (overWeight && withinWeightMargin && !overCount) {
            double overBy = newTotalWeight - maxWeight;
            return new ExecuteResult(ExecuteResultType.ACCEPTED_WITH_WARN,
                    String.format("最大重量が%.1fKgオーバーしました。", overBy));
        }

        if (overCount && withinCountMargin && !overWeight) {
            int overBy = newTotalCount - maxCount;
            return new ExecuteResult(ExecuteResultType.ACCEPTED_WITH_WARN,
                    String.format("最大収容数を%d匹超えています。", overBy));
        }

        return new ExecuteResult(ExecuteResultType.REJECTED, "収容条件を満たしていません。");
    }
}
