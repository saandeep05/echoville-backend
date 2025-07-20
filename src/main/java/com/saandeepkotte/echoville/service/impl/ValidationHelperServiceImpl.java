package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.repository.CommunityRepository;
import com.saandeepkotte.echoville.repository.CompanyRepository;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationHelperServiceImpl implements ValidationHelperService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public boolean isValidCompany(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        return company.isPresent();
    }

    @Override
    public boolean isValidCommunity(String companyId, Long communityId) {
        Community community = communityRepository.findByIdAndCompanyId(communityId, companyId);
        return community != null;
    }
}
