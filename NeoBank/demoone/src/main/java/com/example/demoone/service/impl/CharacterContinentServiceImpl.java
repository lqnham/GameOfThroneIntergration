package com.example.demoone.service.impl;

import com.example.demoone.dto.Characters;
import com.example.demoone.dto.Continents;
import com.example.demoone.dto.request.CharacterContinentRequest;
import com.example.demoone.service.CharacterContinentService;
import com.example.demoone.service.ThirdPartyService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterContinentServiceImpl implements CharacterContinentService {
    private final ThirdPartyService thirdPartyService;
    @Override
    public List<Characters> getCharacterContinent(CharacterContinentRequest request) {
        List<Characters> charactersList = thirdPartyService.convertCharactersFluxToList(thirdPartyService.fetchCharacters());
        List<Continents> continentsFlux = thirdPartyService.convertContinentsFluxToList(thirdPartyService.fetchContinents());

        charactersList.stream().forEach(characters -> {
            int idNe = characters.getId();
            if(idNe % 2 == 0){
                buildContinentValue(characters, continentsFlux, 0);
            }else {
                if(idNe % 3 == 0 && idNe % 5 == 0){
                    buildContinentValue(characters, continentsFlux, 3);
                }else if (idNe % 3 == 0 || idNe % 5 == 0){
                    buildContinentValue(characters, continentsFlux, 2);
                }else{
                    buildContinentValue(characters, continentsFlux, 1);
                }
            }
        });
        return buildFilter(request, charactersList);
    }

    private static List<Characters> buildFilter(CharacterContinentRequest request, List<Characters> charactersList) {
        List<Characters> filteredList;
        if(validFamily(request) && validTitle(request)) {
            filteredList = charactersList.stream()
                    .filter(characterObj -> request.getTitle().equalsIgnoreCase(characterObj.getTitle()))
                    .filter(characterObj -> request.getFamily().equalsIgnoreCase(characterObj.getFamily()))
                    .collect(Collectors.toList());
        }else{
            if(validFamily(request)){
                filteredList = charactersList.stream()
                        .filter(characterObj -> request.getFamily().equalsIgnoreCase(characterObj.getFamily()))
                        .collect(Collectors.toList());
            }else if(validTitle(request)){
                filteredList = charactersList.stream()
                        .filter(characterObj -> request.getTitle().equalsIgnoreCase(characterObj.getTitle()))
                        .collect(Collectors.toList());
            }else{
                filteredList = charactersList;
            }
        }
        return filteredList;
    }

    private static boolean validTitle(CharacterContinentRequest request) {
        return StringUtils.isNotEmpty(request.getTitle());
    }

    private static boolean validFamily(CharacterContinentRequest request) {
        return StringUtils.isNotEmpty(request.getFamily());
    }

    private static void buildContinentValue(Characters characters, List<Continents> continentsFlux, int index) {
        characters.setContinentId(continentsFlux.get(index).getId());
        characters.setContinentName(continentsFlux.get(index).getName());
    }
}
