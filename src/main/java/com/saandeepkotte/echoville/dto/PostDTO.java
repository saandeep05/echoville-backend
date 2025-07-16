package com.saandeepkotte.echoville.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    @NotBlank(message = "Post title is required")
    private String title;
    @NotBlank(message = "Post content is required")
    private String content;
    private boolean isPublic;
    @NotBlank(message = "Community is required")
    private Long communityId;
    @NotBlank(message = "User is required")
    private Long creatorId;
}
