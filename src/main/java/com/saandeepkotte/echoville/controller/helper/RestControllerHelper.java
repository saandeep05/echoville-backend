package com.saandeepkotte.echoville.controller.helper;

import com.saandeepkotte.echoville.dto.EntityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class RestControllerHelper {
    public static EntityDTO getResponseEntity(Object data, String message) {
        List<String> errors = new ArrayList<>();
        errors.add(message);
        EntityDTO entityDTO = new EntityDTO<>(errors, message, data);
        return entityDTO;
    }
}
