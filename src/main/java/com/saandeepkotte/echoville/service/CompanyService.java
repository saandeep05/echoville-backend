package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.CompanyCreateDTO;
import com.saandeepkotte.echoville.model.Company;

public interface CompanyService extends BaseService<Company> {
    Company createNewCompany(CompanyCreateDTO companyCreateDTO);
}
