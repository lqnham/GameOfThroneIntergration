package com.example.demoone.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Characters {
    private int id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String family;
    private String image;
    private String imageUrl;

    private int continentId;
    private String continentName;
}
