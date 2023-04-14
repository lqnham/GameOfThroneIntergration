package com.example.demoone.service;

import com.example.demoone.dto.Characters;
import com.example.demoone.dto.request.CharacterContinentRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResponseCustom {
    private static final int TOTAL_ITEM_EACH_PAGE = 10;
    public static Map<String, Object> buildingResponseIncludePaging(CharacterContinentRequest request, List<Characters> characterContinent) {
        int totalPage = (characterContinent.size() / TOTAL_ITEM_EACH_PAGE) + 1;
        int currentPage = (request.getPage() - 1) * TOTAL_ITEM_EACH_PAGE;

        List<Characters> characterContinentPaged = characterContinent.stream()
                .skip(currentPage)
                .limit(request.getSize()).collect(Collectors.toList());



        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("characters", characterContinentPaged) ;
        responseMap.put("size", characterContinentPaged.size());
        responseMap.put("page", request.getPage());
        responseMap.put("totalPage", totalPage);
        return responseMap;
    }
}
