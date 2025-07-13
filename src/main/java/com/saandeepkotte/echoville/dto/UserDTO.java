package com.saandeepkotte.echoville.dto;

import com.saandeepkotte.echoville.utils.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    private String phone;
    private String companyId;
    private Long communityId;
    private Long houseId;
    private List<Long> issueIds;
    private List<Long> postId;
    private UserRole role;
    private Long id;
}
