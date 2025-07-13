package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.CommunityDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.repository.CommunityRepository;
import com.saandeepkotte.echoville.service.CommunityService;
import com.saandeepkotte.echoville.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public CommunityDTO createNewCommunity(CommunityDTO communityDTO) throws EchoException {
        String companyId = communityDTO.getCompanyId();
        Company company = companyService.getCompany(companyId);
        if(company == null) {
            throw new EchoException("Could not find the given company");
        }
        Community community = new Community();
        community.setCompany(company);
        community.setName(communityDTO.getName());
        community.setLocation(communityDTO.getLocation());

        community = communityRepository.save(community);
        return community.toDto();
    }
}
