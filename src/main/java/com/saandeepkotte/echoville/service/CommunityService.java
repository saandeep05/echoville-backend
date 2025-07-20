package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.CommunityDTO;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;
import jakarta.validation.Valid;

import java.util.List;

public interface CommunityService extends BaseService<Community> {
    CommunityDTO createNewCommunity(CommunityDTO communityDTO) throws EchoException;

    List<CommunityDTO> getAllCommunities(String companyId, Long communityId);

    List<CommunityDTO> getCommunitiesByCompanyId(String companyId);

    List<UserDTO> getResidents(String companyId, Long communityId);

    UserDTO createNewResident(String companyId, Long communityId, @Valid UserDTO userDTO);

    List<HouseDTO> createNewHouses(String companyId, Long communityId, @Valid List<HouseDTO> houseDTOList);

    List<HouseDTO> getAllHouses(String companyId, Long communityId);
}
