package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.CompanyCreateDTO;
import com.saandeepkotte.echoville.dto.CompanyDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.repository.CompanyRepository;
import com.saandeepkotte.echoville.service.CompanyService;
import com.saandeepkotte.echoville.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public CompanyDTO editCompany(String companyId, CompanyDTO companyDTO) {
        Company company = getCompany(companyId);
        company.setName(companyDTO.getName());
        company = companyRepository.save(company);
        return company.toDto();
    }

    @Override
    public Boolean deleteCompany(String companyId, boolean isSoftDelete) {
        if(isSoftDelete) {
            Company company = getCompany(companyId);
            company.setDeletedAt(LocalDateTime.now());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
