package com.example.training_animal_application.usecase;

import com.example.training_animal_application.dto.Api3001RequestDto;
import com.example.training_animal_application.dto.Api3001ResponseDto;
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

  // 檻に収容するための比較をおこなう
  public Api3001ResponseDto usecase(Api3001RequestDto request) {
  }
}
