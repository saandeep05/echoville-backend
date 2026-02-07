package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.IssueDTO;
import com.saandeepkotte.echoville.model.Issue;

import java.util.List;

public interface IssueService extends BaseService<Issue> {
    public List<IssueDTO> getAllIssuesForCommunity(String companyId, Long communityId);

    List<IssueDTO> getAllIssueForUser(String companyId, Long communityId, Long userId);
}
