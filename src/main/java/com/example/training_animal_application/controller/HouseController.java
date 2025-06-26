package com.example.training_animal_application.controller;

import com.example.training_animal_application.dto.Api3001RequestDto;
import com.example.training_animal_application.dto.Api3001ResponseDto;
import com.example.training_animal_application.usecase.Api3001Usecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HouseController {
  private final Api3001Usecase api3001Usecase;

  @PostMapping("api/house/post")
  public Api3001ResponseDto list(@RequestBody Api3001RequestDto request){
    return api3001Usecase.usecase(request);
  }
}
