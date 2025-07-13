package com.saandeepkotte.echoville.controller.helper;

import com.saandeepkotte.echoville.dto.EntityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class RestControllerHelper {
    public static EntityDTO getResponseEntity(Object data) {
        EntityDTO entityDTO = new EntityDTO<>(new ArrayList<>(), "", data);
        return entityDTO;
    }
}
