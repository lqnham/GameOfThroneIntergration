package com.example.demoone.service;

import com.example.demoone.dto.Characters;
import com.example.demoone.dto.request.CharacterContinentRequest;

import java.util.List;

public interface CharacterContinentService {
    List<Characters> getCharacterContinent(CharacterContinentRequest request);
}
