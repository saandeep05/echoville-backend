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
}
