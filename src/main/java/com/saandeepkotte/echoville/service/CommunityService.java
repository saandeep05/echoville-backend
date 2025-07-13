package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.CommunityDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Community;

public interface CommunityService extends BaseService {
    CommunityDTO createNewCommunity(CommunityDTO communityDTO) throws EchoException;
}
