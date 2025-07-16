package com.saandeepkotte.echoville.dto;

import com.saandeepkotte.echoville.utils.enums.IssueStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    private Long id;
    @NotBlank(message = "Issue title is required")
    private String title;
    @NotBlank(message = "Issue description is required")
    private String desctiption;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    @NotBlank(message = "Community is required")
    private Long communityId;
    @NotBlank(message = "Resident is required")
    private Long residentId;
}
