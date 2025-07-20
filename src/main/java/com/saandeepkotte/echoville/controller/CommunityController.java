package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.CommunityDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.service.CommunityService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @PostMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<CommunityDTO>> createNewCommunity(@Valid @RequestBody CommunityDTO communityDTO) {
        EntityDTO<CommunityDTO> entityDTO = null;
        try {
            CommunityDTO community = communityService.createNewCommunity(communityDTO);
            entityDTO = RestControllerHelper.getResponseEntity(community, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<EntityDTO<List<CommunityDTO>>> getAllCommunities(@RequestHeader("companyId") String companyId,
                                                                           @RequestParam(name = "communityId", required = false) Long communityId) {
        EntityDTO<List<CommunityDTO>> entityDTO = null;
        try {
            List<CommunityDTO> communityDTOs = communityService.getAllCommunities(companyId, communityId);
            entityDTO = RestControllerHelper.getResponseEntity(communityDTOs, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.COMMUNITY_RESIDENTS)
    public ResponseEntity<EntityDTO<List<UserDTO>>> getAllResidents(@RequestHeader("companyId") String companyId,
                                                                    @PathVariable("communityId") Long communityId) {
        EntityDTO<List<UserDTO>> entityDTO = null;
        try {
            List<UserDTO> userDTOList = communityService.getResidents(companyId, communityId);
            entityDTO = RestControllerHelper.getResponseEntity(userDTOList, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
