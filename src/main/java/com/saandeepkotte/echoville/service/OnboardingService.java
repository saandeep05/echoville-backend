package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;

public interface OnboardingService {
    public Company getCompany(String id);

    public Community getCommunity(Long id);
}
