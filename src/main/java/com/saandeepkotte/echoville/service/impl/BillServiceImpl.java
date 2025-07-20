package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Bill;
import com.saandeepkotte.echoville.repository.BillRepository;
import com.saandeepkotte.echoville.service.BillService;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import com.saandeepkotte.echoville.utils.enums.BillStatus;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends BaseServiceImpl<Bill, Long> implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ValidationHelperService validationHelperService;

    @Override
    public BillDTO createNewBill(String companyId, BillDTO billDTO) {
        Pair<Boolean, String> validation = validationHelperService.isValidHouseOfCommunity(companyId, billDTO.getCommunityId(), billDTO.getHouseId());
        if(!validation.getKey()) {
            throw new EchoException(validation.getValue());
        }
        billDTO.setStatus(BillStatus.UNPAID);
        Bill bill = new Bill(billDTO);
        bill = billRepository.save(bill);
        return bill.toDto();
    }
}
