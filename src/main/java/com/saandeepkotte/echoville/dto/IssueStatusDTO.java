package com.saandeepkotte.echoville.dto;

import com.saandeepkotte.echoville.utils.enums.IssueStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IssueStatusDTO {
    @NotNull(message = "Issue id cannot be null")
    private Long issueId;
    @NotNull(message = "Issue status cannot be null")
    private IssueStatus issueStatus;
    @NotNull(message = "User id cannot be null")
    private Long userId;
    @NotNull(message = "Community id cannot be null")
    private Long communityId;
}
