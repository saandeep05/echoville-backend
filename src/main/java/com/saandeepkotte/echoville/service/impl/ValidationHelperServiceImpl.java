package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.EchoUser;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.repository.CommunityRepository;
import com.saandeepkotte.echoville.repository.CompanyRepository;
import com.saandeepkotte.echoville.repository.EchoUserRepository;
import com.saandeepkotte.echoville.repository.HouseRepository;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValidationHelperServiceImpl implements ValidationHelperService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private EchoUserRepository userRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Override
    public boolean isValidCompany(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        return company.isPresent();
    }

    @Override
    public boolean isValidCommunity(String companyId, Long communityId) {
        Community community = communityRepository.findByIdAndCompanyId(communityId, companyId);
        return community != null;
    }

    @Override
    public Pair<Boolean, String> isValidUserOfCommunity(String companyId, Long communityId, UserDTO userDTO) {
        if(!isValidCommunity(companyId, communityId)) {
            return new Pair<>(false, "Invalid Community");
        }
        List<EchoUser> user = userRepository.findByEmailAndCommunityIdAndCompanyId(userDTO.getEmail(), communityId, companyId);
        return user.isEmpty() ? new Pair<>(true, "") : new Pair<>(false, "User with this email already exists");
    }

    @Override
    public Pair<Boolean, String> isValidHouseOfCommunity(String companyId, Long communityId, HouseDTO houseDTO) {
        if(!isValidCommunity(companyId, communityId)) {
            new Pair<>(false, "Invalid Community");
        }
        List<House> houses = houseRepository.findByNumber(houseDTO.getNumber());
        return houses.isEmpty() ? new Pair<>(true, "") : new Pair<>(false, "House with number " + houseDTO.getNumber() + " already exists");
    }
}
