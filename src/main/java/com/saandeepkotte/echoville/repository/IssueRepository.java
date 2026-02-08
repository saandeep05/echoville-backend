package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends BaseRepository<Issue, Long> {
    List<Issue> findByCommunityId(Long communityId);

    @Query("select I from Issue I where I.raisedBy.id = :userId")
    List<Issue> findByUserId(@Param("userId") Long userId);
}
