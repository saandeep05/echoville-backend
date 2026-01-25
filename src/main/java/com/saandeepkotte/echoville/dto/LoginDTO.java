package com.saandeepkotte.echoville.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Need to be a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, max = 25, message = "Password require minimum 5 and maximum 25 letters")
    private String password;
}
