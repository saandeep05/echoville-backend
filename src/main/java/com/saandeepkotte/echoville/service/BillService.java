package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.model.Bill;
import jakarta.validation.Valid;

public interface BillService extends BaseService<Bill> {
    BillDTO createNewBill(String companyId, BillDTO billDTO);
}
