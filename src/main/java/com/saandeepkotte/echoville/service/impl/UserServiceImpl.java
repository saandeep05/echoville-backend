package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;
import com.saandeepkotte.echoville.repository.EchoUserRepository;
import com.saandeepkotte.echoville.service.UserService;
import com.saandeepkotte.echoville.utils.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EchoUserRepository userRepository;

    @Override
    public EchoUser createNewUserForCompany(UserDTO userDTO, Company company) throws EchoException {
        String email = userDTO.getEmail();
        if(isAdminAlreadyPresent(email, company.getId())) {
            throw new EchoException("A company admin with email id " + email + " already exists for company " + company.getName());
        }
        EchoUser user = new EchoUser();
        user.setCompany(company);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(UserRole.COMPANY_ADMIN);
        return this.saveUser(user);
    }

    private EchoUser saveUser(EchoUser user) {
        // impl: space left for implementing password encoding
        return userRepository.save(user);
    }

    private boolean isAdminAlreadyPresent(String email, String companyId) {
        Integer count = getAdminsWithEmail(email, companyId);
        return count != 0;
    }

    private Integer getAdminsWithEmail(String email, String companyId) {
        return userRepository.getAdminCount(email, companyId);
    }
}
