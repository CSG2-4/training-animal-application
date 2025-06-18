package com.example.training_animal_application.store;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class StoreBase<V> extends HashMap<String, V> {
    public void insert(String k, V v) {
        put(k, v);
    }

    public List<V> findAll() {
        return entrySet().stream().sorted(Comparator.comparing(Entry::getKey)).map(Entry::getValue).toList();
    }
}
