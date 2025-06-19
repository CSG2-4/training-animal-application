package com.example.training_animal_application.store;

import java.util.List;

public interface Store<V> {
    void insert(String k, V v);

    List<V> findAll();
}
