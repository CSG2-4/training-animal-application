package com.example.training_animal_application.model;

public interface HousePolicy {
    ExecuteResult execute(Cage cage, Animal animal);

    enum ExecuteResultType {
        ACCEPTED,
        ACCEPTED_WITH_WARN,
        REJECTED
    }

    // public + static にすることで、外部からアクセス可能に
    public static class ExecuteResult {
        public final ExecuteResultType result;
        public final String message;

        public ExecuteResult(ExecuteResultType result, String message) {
            this.result = result;
            this.message = message;
        }
    }
}