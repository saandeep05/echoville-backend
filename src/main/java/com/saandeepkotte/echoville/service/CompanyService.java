package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.CompanyCreateDTO;
import com.saandeepkotte.echoville.dto.CompanyDTO;
import com.saandeepkotte.echoville.model.Company;
import jakarta.validation.Valid;

public interface CompanyService extends BaseService<Company> {
    Company createNewCompany(CompanyCreateDTO companyCreateDTO);

    Company getCompany(String companyId);

    CompanyDTO editCompany(String companyId, CompanyDTO companyDTO);

    Boolean deleteCompany(String companyId, boolean isSoft);
}
