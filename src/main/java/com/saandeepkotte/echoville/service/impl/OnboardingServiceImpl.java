package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.repository.CommunityRepository;
import com.saandeepkotte.echoville.repository.CompanyRepository;
import com.saandeepkotte.echoville.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingServiceImpl implements OnboardingService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CommunityRepository communityRepository;

    public Company getCompany(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Community getCommunity(Long id) {
        return communityRepository.findById(id).orElse(null);
    }
}
