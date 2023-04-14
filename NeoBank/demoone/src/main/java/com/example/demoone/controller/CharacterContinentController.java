package com.example.demoone.controller;

import com.example.demoone.dto.Characters;
import com.example.demoone.dto.request.CharacterContinentRequest;
import com.example.demoone.service.CharacterContinentService;
import com.example.demoone.service.ResponseCustom;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/character-continent/")
@RequiredArgsConstructor
public class CharacterContinentController {

    private final CharacterContinentService characterContinentService;
    private final ResponseCustom responseCustom;

    @GetMapping(path = "list")
    public ResponseEntity<Map<String, Object>> list(CharacterContinentRequest request){
        List<Characters> characterContinent = characterContinentService.getCharacterContinent(request);
        return new ResponseEntity(responseCustom.buildingResponseIncludePaging(request, characterContinent),
                HttpStatus.OK);
    }


}
