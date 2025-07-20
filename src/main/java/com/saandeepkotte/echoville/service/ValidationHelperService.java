package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.UserDTO;
import javafx.util.Pair;

public interface ValidationHelperService {
    boolean isValidCompany(String companyId);
    boolean isValidCommunity(String companyId, Long communityId);
    Pair<Boolean, String> isValidUserOfCommunity(String companyId, Long communityId, UserDTO userDTO);
}
