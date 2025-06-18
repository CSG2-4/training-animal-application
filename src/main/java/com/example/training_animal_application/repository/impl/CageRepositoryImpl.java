package com.example.training_animal_application.repository.impl;

import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.vo.Size;
import com.example.training_animal_application.model.vo.Weight;
import com.example.training_animal_application.repository.CageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CageRepositoryImpl implements CageRepository {

    private final Map<String, Cage> store;

    public CageRepositoryImpl() {
        store = new HashMap<>();
        Cage c1 = new Cage("0001", "普通の檻", new Weight(500), new Size(5));
        Cage c2 = new Cage("0002", "頑丈な檻", new Weight(9999),  new Size(5));
        Cage c3 = new Cage("0003", "広いな檻", new Weight(500),  new Size(99));
        store.put(c1.getCageId(), c1);
        store.put(c2.getCageId(), c2);
        store.put(c3.getCageId(), c3);
    }

    @Override
    public List<Cage> fetch() {
        return store.values().stream().sorted(Comparator.comparing(Cage::getCageId)).toList();
    }
}
