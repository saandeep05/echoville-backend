package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.Issue;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends BaseRepository<Issue, Long> {
    List<Issue> findByCommunityIdOrderByCreatedAtDesc(Long communityId);

    @Query("select I from Issue I where I.raisedBy.id = :userId order by I.createdAt desc")
    List<Issue> findByUserId(@Param("userId") Long userId);

    Issue findByIdAndCommunityId(@NotNull(message = "Issue id cannot be null") Long issueId, Long communityId);
}
