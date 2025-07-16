package com.saandeepkotte.echoville.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String id;
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    private List<Long> communityId;
}
