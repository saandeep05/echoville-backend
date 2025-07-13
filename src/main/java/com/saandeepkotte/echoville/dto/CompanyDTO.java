package com.saandeepkotte.echoville.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    @NotBlank(message = "Company name cannot be blank")
    private String name;
}
