package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.Community;

import java.util.List;

public interface CommunityRepository extends BaseRepository<Community, Long> {
    List<Community> findByCompanyId(String companyId);

    Community findByIdAndCompanyId(Long communityId, String companyId);
}
