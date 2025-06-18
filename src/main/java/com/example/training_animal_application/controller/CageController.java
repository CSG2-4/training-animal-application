package com.example.training_animal_application.controller;

import com.example.training_animal_application.dto.Api0001RequestDto;
import com.example.training_animal_application.dto.Api0001ResponseDto;
import com.example.training_animal_application.usecase.Api0001Usecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CageController {
    private final Api0001Usecase api0001Usecase;

    @PostMapping("api/cage/list")
    public Api0001ResponseDto list(@RequestBody Api0001RequestDto request){
        return api0001Usecase.usecase(request);
    }
}
