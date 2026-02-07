package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.service.HouseService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @GetMapping(RequestPathURLs.HOUSE_BILL)
    public ResponseEntity<EntityDTO<List<BillDTO>>> getBillsForHouse(@RequestHeader("companyId") String companyId,
                                                                     @RequestHeader("communityId") Long communityId,
                                                                     @PathVariable("houseId") Long houseId) {
        EntityDTO<List<BillDTO>> entityDTO = null;
        try {
            List<BillDTO> billDTOList = houseService.getAllBillsForHouse(companyId, communityId, houseId);
            entityDTO = RestControllerHelper.getResponseEntity(billDTOList, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PostMapping(RequestPathURLs.FOR_COMMUNITY)
    public ResponseEntity<EntityDTO<List<HouseDTO>>> createHouses(@RequestHeader("companyId") String companyId,
                                                                  @PathVariable("communityId") Long communityId,
                                                                  @Valid @RequestBody List<HouseDTO> houseDTOList) {
        EntityDTO<List<HouseDTO>> entityDTO = null;
        try {
            houseDTOList = houseService.createHousesInBulk(companyId, communityId, houseDTOList);
            entityDTO = RestControllerHelper.getResponseEntity(houseDTOList, null);
        } catch (EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.FOR_COMMUNITY)
    public ResponseEntity<EntityDTO<List<HouseDTO>>> getAllHouses(@RequestHeader("companyId") String companyId,
                                                                  @PathVariable("communityId") Long communityId) {
        EntityDTO<List<HouseDTO>> entityDTO = null;
        try {
            List<HouseDTO> houseDTOList = houseService.getAllHousesOfCommunity(companyId, communityId);
            entityDTO = RestControllerHelper.getResponseEntity(houseDTOList, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.GET_HOUSE)
    public ResponseEntity<EntityDTO<HouseDTO>> getHouse(@RequestHeader("companyId") String companyId,
                                                        @RequestHeader("communityId") Long communityId,
                                                        @PathVariable("houseId") Long houseId) {
        EntityDTO<HouseDTO> entityDTO = null;
        try {
            House house = houseService.getHouse(houseId);
            entityDTO = RestControllerHelper.getResponseEntity(house.toDto(), null);
        } catch(Exception e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
