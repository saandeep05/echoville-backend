package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.LoginDTO;
import com.saandeepkotte.echoville.dto.LoginResponseDTO;
import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.service.UserService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = {RequestPathURLs.CREATE_ADMIN, RequestPathURLs.CREATE_ADMIN + "/{communityId}"})
    public ResponseEntity<EntityDTO<LoginResponseDTO>> createAdmin(@Valid @RequestBody UserDTO userDTO, @PathVariable("companyId") String companyId, @PathVariable("communityId") Optional<Long> communityId) {
        EntityDTO<LoginResponseDTO> entityDTO = null;
        try {
            UserDTO user = userService.createAdmin(userDTO, companyId, communityId);
            LoginDTO loginDTO = new LoginDTO(userDTO.getEmail(), userDTO.getPassword());
            String token = userService.verifyUser(companyId, loginDTO);
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user, token);
            entityDTO = RestControllerHelper.getResponseEntity(loginResponseDTO, "");
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PostMapping(RequestPathURLs.ASSIGN_HOUSE)
    public ResponseEntity<EntityDTO<UserDTO>> assignHouse(@RequestHeader("companyId") String companyId,
                                                          @RequestHeader("communityId") Long communityId,
                                                          @PathVariable("userId") Long userId,
                                                          @PathVariable("houseId") Long houseId) {
        EntityDTO<UserDTO> entityDTO = null;
        try {
            UserDTO userDTO = userService.assignHouseToResident(companyId, communityId, userId, houseId);
            entityDTO = RestControllerHelper.getResponseEntity(userDTO, null);
        } catch (EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PostMapping(RequestPathURLs.LOGIN)
    public ResponseEntity<EntityDTO<LoginResponseDTO>> login(@RequestHeader("companyId") String companyId,
                                                   @RequestBody LoginDTO loginDTO) {
        EntityDTO<LoginResponseDTO> entityDTO = null;
        try {
            String token = userService.verifyUser(companyId, loginDTO);
            UserDTO userDTO = userService.getUserWithEmail(loginDTO.getEmail());
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(userDTO, token);
            entityDTO = RestControllerHelper.getResponseEntity(loginResponseDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
