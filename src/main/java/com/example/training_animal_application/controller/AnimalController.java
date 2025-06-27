package com.example.training_animal_application.controller;

import com.example.training_animal_application.dto.Api1001RequestDto;
import com.example.training_animal_application.dto.Api1001ResponseDto;
import com.example.training_animal_application.usecase.Api1001Usecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnimalController {
    private final Api1001Usecase api1001Usecase;

    @PostMapping("api/animal/list")
    public Api1001ResponseDto list(@RequestBody Api1001RequestDto request){
        return api1001Usecase.usecase(request);
    }
}
