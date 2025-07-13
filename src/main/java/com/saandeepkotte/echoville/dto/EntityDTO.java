package com.saandeepkotte.echoville.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityDTO<T> {
    private List<String> errors;
    private String message;
    private T data;
}
