package com.example.demoone.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterContinentRequest {

    private String title;
    private String family;
    private int size;
    private int page;
}
