package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.CompanyCreateDTO;
import com.saandeepkotte.echoville.dto.CompanyDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.service.CompanyService;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<Company>> createNewCompany(@Valid @RequestBody CompanyCreateDTO companyCreateDTO) {
        EntityDTO<Company> entityDTO = null;
        try{
            Company company = companyService.createNewCompany(companyCreateDTO);
            entityDTO = RestControllerHelper.getResponseEntity(company, null);
        } catch (EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.DEFAULT)
    public ResponseEntity<EntityDTO<List<CompanyDTO>>> getAllCompanies() {
        EntityDTO<List<CompanyDTO>> entityDTO = null;
        try {
            List<Company> company = companyService.getAll();
            entityDTO = RestControllerHelper.getResponseEntity(company.stream().map(Company::toDto).toList(), null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.FOR_COMPANY_ID)
    public ResponseEntity<EntityDTO<CompanyDTO>> getCompany(@PathVariable("companyId") String companyId) {
        EntityDTO<CompanyDTO> entityDTO = null;
        try {
            CompanyDTO companyDTO = companyService.getCompany(companyId).toDto();
            entityDTO = RestControllerHelper.getResponseEntity(companyDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PutMapping(RequestPathURLs.FOR_COMPANY_ID)
    public ResponseEntity<EntityDTO<CompanyDTO>> editCompany(@Valid @RequestBody CompanyDTO companyDTO, @PathVariable("companyId") String companyId) {
        EntityDTO<CompanyDTO> entityDTO = null;
        try {
            CompanyDTO company = companyService.editCompany(companyId, companyDTO);
            entityDTO = RestControllerHelper.getResponseEntity(company, null);
        } catch (EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @DeleteMapping(RequestPathURLs.FOR_COMPANY_ID)
    public ResponseEntity<EntityDTO<Boolean>> deleteCompany(@PathVariable("companyId") String companyId) {
        EntityDTO<Boolean> entityDTO = null;
        try {
            Boolean isDeleted = companyService.deleteCompany(companyId, true);
            entityDTO = RestControllerHelper.getResponseEntity(isDeleted, isDeleted ? "success" : "failed");
        } catch (EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(false, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
