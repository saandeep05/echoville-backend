package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;
import com.saandeepkotte.echoville.repository.EchoUserRepository;
import com.saandeepkotte.echoville.service.OnboardingService;
import com.saandeepkotte.echoville.service.UserService;
import com.saandeepkotte.echoville.utils.enums.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<EchoUser, Long> implements UserService {
    @Autowired
    private EchoUserRepository userRepository;
    @Autowired
    private OnboardingService onboardingService;

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
