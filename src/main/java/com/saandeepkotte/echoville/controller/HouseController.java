package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.service.HouseService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
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
}
