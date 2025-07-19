package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.CommunityDTO;
import com.saandeepkotte.echoville.exception.EchoException;

public interface CommunityService extends BaseService {
    CommunityDTO createNewCommunity(CommunityDTO communityDTO) throws EchoException;
}
