package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.repository.EchoUserRepository;
import com.saandeepkotte.echoville.service.OnboardingService;
import com.saandeepkotte.echoville.service.UserService;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import com.saandeepkotte.echoville.utils.enums.UserRole;
import jakarta.transaction.Transactional;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<EchoUser, Long> implements UserService {
    @Autowired
    private EchoUserRepository userRepository;
    @Autowired
    private OnboardingService onboardingService;
    @Autowired
    private ValidationHelperService validationHelperService;

    @Override
    @Transactional
    public UserDTO createAdmin(UserDTO userDTO, String companyId, Optional<Long> communityId) throws EchoException {
        String email = userDTO.getEmail();
        Company company = onboardingService.getCompany(companyId);
        if(company == null || isAdminAlreadyPresent(email, companyId)) {
            throw new EchoException("Could not find the given company or a company admin is already present with the email id " + email);
        }
        EchoUser user = getUserFromDTO(userDTO);
        user.setCompany(company);
        if(communityId.isEmpty()) {
            user.setRole(UserRole.COMPANY_ADMIN);
        } else {
            Community community = onboardingService.getCommunity(communityId.get());
            if(community == null || isAdminAlreadyPresent(email, communityId.get())) {
                throw new EchoException("Could not find the given community in the company " + company.getName() + " or an admin is already present with the email id " + email);
            }
            user.setCommunity(community);
            user.setRole(UserRole.COMMUNITY_ADMIN);
        }
        user = this.saveUser(user);
        return user.toDto();
    }

    @Override
    public UserDTO createNewResident(String companyId, Long communityId, UserDTO userDTO) {
        Pair<Boolean, String> validation = validationHelperService.isValidUserOfCommunity(companyId, communityId, userDTO);
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        userDTO.setCompanyId(companyId);
        userDTO.setCommunityId(communityId);
        userDTO.setRole(UserRole.RESIDENT);
        EchoUser user = new EchoUser(userDTO);
        user = this.saveUser(user);
        return user.toDto();
    }

    @Override
    public UserDTO assignHouseToResident(String companyId, Long communityId, Long userId, Long houseId) {
        Pair<Boolean, String> validation = validationHelperService.isValidUserOfCommunity(companyId, communityId, userId);
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        validation = validationHelperService.isValidHouseOfCommunity(companyId, communityId, houseId);
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        EchoUser user = userRepository.findById(userId).get();
        if(user.getRole() != UserRole.RESIDENT) {
            throw new EchoException("User is not a resident");
        }
        House house = new House();
        house.setId(houseId);
        user.setHouse(house);
        user = this.saveUser(user);
        return user.toDto();
    }

    private EchoUser saveUser(EchoUser user) {
        // impl: space left for implementing password encoding
        return userRepository.save(user);
    }

    private boolean isAdminAlreadyPresent(String email, String companyId) {
        Integer count = getAdminsWithEmail(email, companyId);
        return count != 0;
    }

    private boolean isAdminAlreadyPresent(String email, Long communityId) {
        Integer count = getAdminsWithEmail(email, communityId);
        return count != 0;
    }

    private Integer getAdminsWithEmail(String email, String companyId) {
        return userRepository.getAdminCount(email, companyId);
    }

    private Integer getAdminsWithEmail(String email, Long communityId) {
        return userRepository.getAdminCount(email, communityId);
    }

    private EchoUser getUserFromDTO(UserDTO userDTO) {
        EchoUser user = new EchoUser();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }
}
