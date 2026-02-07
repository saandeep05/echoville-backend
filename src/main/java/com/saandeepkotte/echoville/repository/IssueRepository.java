package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends BaseRepository<Issue, Long> {
    List<Issue> findByCommunityId(Long communityId);
}
