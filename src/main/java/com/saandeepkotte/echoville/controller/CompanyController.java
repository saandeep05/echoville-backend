package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.CompanyCreateDTO;
import com.saandeepkotte.echoville.dto.CompanyDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.service.CompanyService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<Company>> createNewCompany(@Valid @RequestBody CompanyCreateDTO companyCreateDTO) {
        Company company = companyService.createNewCompany(companyCreateDTO);
        EntityDTO<Company> entityDTO = RestControllerHelper.getResponseEntity(company);
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
