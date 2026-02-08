package com.saandeepkotte.echoville.dto;

import com.saandeepkotte.echoville.utils.enums.IssueStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    private Long id;
    @NotBlank(message = "Issue title is required")
    private String title;
    @NotBlank(message = "Issue description is required")
    private String description;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    @NotNull(message = "Community is required")
    private Long communityId;
    @NotNull(message = "Resident is required")
    private UserDTO residentDTO;
    private LocalDateTime createdAt;
}
