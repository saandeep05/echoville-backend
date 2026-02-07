package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.dto.LoginDTO;
import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<EchoUser> {
    UserDTO createAdmin(UserDTO userDTO, String companyId, Optional<Long> communityId) throws EchoException;

    UserDTO createNewResident(String companyId, Long communityId, UserDTO userDTO);

    UserDTO assignHouseToResident(String companyId, Long communityId, Long userId, Long houseId);

    UserDTO getUserWithEmail(String email);

    String verifyUser(String companyId, LoginDTO loginDTO);

    List<UserDTO> getResidents(String companyId, Long communityId);

    HouseDTO getHouse(String companyId, Long communityId, Long userId);
}
