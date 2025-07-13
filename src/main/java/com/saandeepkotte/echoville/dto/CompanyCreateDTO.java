package com.saandeepkotte.echoville.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateDTO {
    @Valid
    private CompanyDTO companyDTO;
    @Valid
    private UserDTO adminDTO;
}
