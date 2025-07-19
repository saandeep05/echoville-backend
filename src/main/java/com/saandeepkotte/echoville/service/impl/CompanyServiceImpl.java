package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.CompanyCreateDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.repository.CompanyRepository;
import com.saandeepkotte.echoville.service.CompanyService;
import com.saandeepkotte.echoville.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, String> implements CompanyService {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public Company createNewCompany(CompanyCreateDTO companyCreateDTO) {
        try {
            Company company = new Company();
            company.setName(companyCreateDTO.getCompanyDTO().getName());
            company = companyRepository.save(company);
            userService.createAdmin(companyCreateDTO.getAdminDTO(), company.getId(), Optional.empty());
            return company;
        } catch (EchoException e) {
            throw e;
        }
    }

    @Override
    public Company getCompany(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isPresent()) {
            return company.get();
        } else {
            throw new EchoException("No company found with given id: " + companyId);
        }
    }
}
