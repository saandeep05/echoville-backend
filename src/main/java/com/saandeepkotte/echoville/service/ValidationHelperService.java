package com.saandeepkotte.echoville.service;

public interface ValidationHelperService {
    boolean isValidCompany(String companyId);
    boolean isValidCommunity(String companyId, Long communityId);
}
