package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.Bill;

import java.util.List;

public interface BillRepository extends BaseRepository<Bill, Long> {
    List<Bill> findByCommunityId(Long communityId);
}
