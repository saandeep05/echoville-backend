package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.dto.HouseDTO;
import com.saandeepkotte.echoville.model.House;

import java.util.List;

public interface HouseService extends BaseService<House> {
    List<HouseDTO> createHousesInBulk(String companyId, Long communityId, List<HouseDTO> houseDTOList);

    List<BillDTO> getAllBillsForHouse(String companyId, Long communityId, Long houseId);
}
