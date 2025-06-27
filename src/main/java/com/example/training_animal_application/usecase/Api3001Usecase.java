package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api3001RequestDto;
import com.example.training_animal_application.dto.Api3001ResponseDto;
import com.example.training_animal_application.model.Animal;
import com.example.training_animal_application.model.Cage;
import com.example.training_animal_application.model.House;
import com.example.training_animal_application.repository.AnimalRepository;
import com.example.training_animal_application.repository.CageRepository;
import com.example.training_animal_application.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Api3001Usecase {

  private final CageRepository cageRepository;
  private final AnimalRepository animalRepository;
  private final HouseRepository houseRepository;

  public Api3001ResponseDto usecase(Api3001RequestDto request) {

    // リクエスト(紙に書かれた各ID)を取得
    String cageId = request.getCageId();
    String animalId = request.getAnimalId();

    // 檻と動物の状態を取得
    Cage cage = cageRepository.findById(cageId);
    Animal animal = animalRepository.findById(animalId);

    // Houseモデルに値を詰める
    House h = new House();
    h.setCageId(cage);
    h.setAnimalId(animal);

    houseRepository.insert(h);

    return null;
  }

}
