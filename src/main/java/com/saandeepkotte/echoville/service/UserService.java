package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;

public interface UserService extends BaseService {
    EchoUser createNewUserForCompany(UserDTO userDTO, Company company) throws EchoException;
}
