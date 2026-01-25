package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.service.BillService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<BillDTO>> createBill(@RequestHeader("companyId") String companyId,
                                                         @Valid @RequestBody BillDTO billDTO) {
        EntityDTO<BillDTO> entityDTO = null;
        try {
            billDTO = billService.createNewBill(companyId, billDTO);
            entityDTO = RestControllerHelper.getResponseEntity(billDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<BillDTO>> getAllBills(@RequestHeader("companyId") String companyId,
                                                          @RequestHeader("communityId") Long communityId) {
        EntityDTO<BillDTO> entityDTO = null;
        try {
            List<BillDTO> billDTOList = billService.getAllBillsOfCommunity(companyId, communityId);
            entityDTO = RestControllerHelper.getResponseEntity(billDTOList, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PutMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<BillDTO>> updateBill(@RequestHeader("companyId") String companyId,
                                                         @RequestBody BillDTO billDTO) {
        EntityDTO<BillDTO> entityDTO = null;
        try {
            billDTO = billService.updateBill(companyId, billDTO);
            entityDTO = RestControllerHelper.getResponseEntity(billDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @DeleteMapping(RequestPathURLs.BILL)
    public ResponseEntity<EntityDTO<BillDTO>> deleteBill(@RequestHeader("companyId") String companyId,
                                                         @RequestHeader("communityId") Long communityId,
                                                         @PathVariable("billId") Long billId) {
        EntityDTO<BillDTO> entityDTO = null;
        try {
            BillDTO billDTO = billService.deleteBill(companyId, communityId, billId);
            entityDTO = RestControllerHelper.getResponseEntity(billDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
