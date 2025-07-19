package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;

import java.util.Optional;

public interface UserService extends BaseService {
    UserDTO createAdmin(UserDTO userDTO, String companyId, Optional<Long> communityId) throws EchoException;
}
