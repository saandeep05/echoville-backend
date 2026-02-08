package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Bill;
import com.saandeepkotte.echoville.model.Community;
import com.saandeepkotte.echoville.model.Company;
import com.saandeepkotte.echoville.model.House;
import com.saandeepkotte.echoville.repository.BillRepository;
import com.saandeepkotte.echoville.service.BillService;
import com.saandeepkotte.echoville.service.HouseService;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import com.saandeepkotte.echoville.utils.enums.BillStatus;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl extends BaseServiceImpl<Bill, Long> implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ValidationHelperService validationHelperService;
    @Autowired
    private HouseService houseService;

    @Override
    public BillDTO createNewBill(String companyId, BillDTO billDTO) {
        Pair<Boolean, String> validation = validationHelperService.isValidHouseOfCommunity(companyId, billDTO.getCommunityId(), billDTO.getHouseId());
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        billDTO.setStatus(BillStatus.UNPAID);
        Bill bill = new Bill(billDTO);
        House house = houseService.getHouse(bill.getHouse().getId());
        bill.setHouse(house);
        bill = billRepository.save(bill);
        return bill.toDto();
    }

    @Override
    public List<BillDTO> getAllBillsOfCommunity(String companyId, Long communityId) {
        boolean isValidCommunity = validationHelperService.isValidCommunity(companyId, communityId);
        if(!isValidCommunity) {
            throw new EchoException("This community does not exist with the company");
        }
        List<Bill> bills = billRepository.findByCommunityId(communityId);
        List<BillDTO> billDTOList = bills.stream().map(Bill::toDto).toList();
        return billDTOList;
    }

    @Override
    public BillDTO updateBill(String companyId, BillDTO billDTO) {
        Pair<Boolean, Object> validation = validationHelperService.isValidBillOfHouse(companyId, billDTO.getCommunityId(), billDTO.getHouseId(), billDTO.getId());
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue().toString());
        }
        Bill bill = (Bill) validation.getValue();
        bill.toModel(billDTO);
        billRepository.save(bill);
        return billDTO;
    }

    @Override
    public BillDTO deleteBill(String companyId, Long communityId, Long billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        if(bill == null) {
            throw new EchoException("Bill not found");
        }
        House house = bill.getHouse();
        Pair<Boolean, String> validation = validationHelperService.isValidHouseOfCommunity(companyId, communityId, house.getId());
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        bill.setDeletedAt(LocalDateTime.now());
        billRepository.save(bill);
        return bill.toDto();
    }
}
