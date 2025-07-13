package com.saandeepkotte.echoville.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    @NotBlank(message = "Community name cannot be blank")
    private String name;
    @NotBlank(message = "Location cannot be blank")
    private String location;
    @NotBlank(message = "Company id cannot be blank")
    private String companyId;
    private List<Long> houses;
    private List<Long> users;
    private List<Long> posts;
    private List<Long> issues;
    private List<Long> bills;
    private Long id;
}
