package com.saandeepkotte.echoville.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {
    private Long id;
    private String block;
    private String floor;
    @NotBlank(message = "House number is required")
    private String number;
    @NotBlank(message = "House type is required")
    private String type;
    @NotNull(message = "Community is required")
    private Long communityId;
    private List<Long> residents;
    private List<Long> bills;
}
