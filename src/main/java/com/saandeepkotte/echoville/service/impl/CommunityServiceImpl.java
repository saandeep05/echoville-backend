package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.CommunityDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.repository.CommunityRepository;
import com.saandeepkotte.echoville.service.*;
import com.saandeepkotte.echoville.utils.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl extends BaseServiceImpl<Community, Long> implements CommunityService {
    @Autowired
    private OnboardingService onboardingService;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private ValidationHelperService validationHelperService;
    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;

    @Override
    public CommunityDTO createNewCommunity(CommunityDTO communityDTO) throws EchoException {
        String companyId = communityDTO.getCompanyId();
        Company company = onboardingService.getCompany(companyId);
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

    @Override
    public List<CommunityDTO> getAllCommunities(String companyId, Long communityId) {
        if(onboardingService.getCompany(companyId) == null) {
            return null;
        }
        if(communityId != null) {
            CommunityDTO communityDTO = getCommunity(companyId, communityId);
            if(communityDTO == null) {
                throw new EchoException("No community found in company id " + companyId + " having community id " + communityId);
            }
            return List.of(communityDTO);
        }
        return getCommunitiesByCompanyId(companyId);
    }

    @Override
    public List<CommunityDTO> getCommunitiesByCompanyId(String companyId) {
        List<Community> communities = communityRepository.findByCompanyId(companyId);
        return communities.stream().map(Community::toDto).toList();
    }

    @Override
    public List<UserDTO> getResidents(String companyId, Long communityId) {
        if(!validationHelperService.isValidCommunity(companyId, communityId)) {
            throw new EchoException("Invalid community");
        }
        Community community = communityRepository.findByIdAndCompanyId(communityId, companyId);
        List<EchoUser> users = community.getUsers().stream().filter(user -> user.getRole() == UserRole.RESIDENT).toList();
        return users.stream().map(EchoUser::toDto).toList();
    }

    @Override
    public UserDTO createNewResident(String companyId, Long communityId, UserDTO userDTO) {
        return userService.createNewResident(companyId, communityId, userDTO);
    }

    @Override
    public List<HouseDTO> createNewHouses(String companyId, Long communityId, List<HouseDTO> houseDTOList) {
        return houseService.createHousesInBulk(companyId, communityId, houseDTOList);
    }

    @Override
    public List<HouseDTO> getAllHouses(String companyId, Long communityId) {
        if(!validationHelperService.isValidCommunity(companyId, communityId)) {
            throw new EchoException("Invalid Community");
        }
        Optional<Community> community = communityRepository.findById(communityId);
        return community.get().getHouses().stream().map(House::toDto).toList();
    }

    public CommunityDTO getCommunity(String companyId, Long communityId) {
        if(validationHelperService.isValidCompany(companyId)) {
            return null;
        }
        Community community = communityRepository.findByIdAndCompanyId(communityId, companyId);
        return community != null ? community.toDto() : null;
    }
}
