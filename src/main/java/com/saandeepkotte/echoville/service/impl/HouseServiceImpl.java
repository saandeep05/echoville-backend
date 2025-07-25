package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Bill;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.repository.HouseRepository;
import com.saandeepkotte.echoville.service.HouseService;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl extends BaseServiceImpl<House, Long> implements HouseService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private ValidationHelperService validationHelperService;

    @Override
    public List<HouseDTO> createHousesInBulk(String companyId, Long communityId, List<HouseDTO> houseDTOList) {
        if(!validationHelperService.isValidCommunity(companyId, communityId)) {
            throw new EchoException("Invalid community");
        }
        List<HouseDTO> duplicatedNumbers = new ArrayList<>();
        houseDTOList = houseDTOList.stream().filter(houseDTO -> {
            Pair<Boolean, String> validation = validationHelperService.isValidHouseOfCommunity(companyId, communityId, houseDTO);
            houseDTO.setCommunityId(communityId);
            if(!validation.getKey()) {
                duplicatedNumbers.add(houseDTO);
            }
            return validation.getKey();
        }).toList();
        List<House> houses = houseDTOList.stream().map(House::new).toList();
        houses = houseRepository.saveAll(houses);
        return houses.stream().map(House::toDto).toList();
    }

    @Override
    public List<BillDTO> getAllBillsForHouse(String companyId, Long communityId, Long houseId) {
        Pair<Boolean, String> validation = validationHelperService.isValidHouseOfCommunity(companyId, communityId, houseId);
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        Optional<House> house = houseRepository.findById(houseId);
        return house.map(value -> value.getBills().stream().map(Bill::toDto).toList()).orElse(new ArrayList<>());
    }
}
