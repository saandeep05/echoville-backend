package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.model.Bill;
import jakarta.validation.Valid;

import java.util.List;

public interface BillService extends BaseService<Bill> {
    BillDTO createNewBill(String companyId, BillDTO billDTO);

    List<BillDTO> getAllBillsOfCommunity(String companyId, Long communityId);

    BillDTO updateBill(String companyId, BillDTO billDTO);

    BillDTO deleteBill(String companyId, Long communityId, Long billId);
}
