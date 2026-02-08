package com.saandeepkotte.echoville.service;

import com.saandeepkotte.echoville.dto.IssueDTO;
import com.saandeepkotte.echoville.dto.IssueStatusDTO;
import com.saandeepkotte.echoville.model.Issue;
import com.saandeepkotte.echoville.utils.enums.IssueStatus;

import java.util.List;

public interface IssueService extends BaseService<Issue> {
    public List<IssueDTO> getAllIssuesForCommunity(String companyId, Long communityId);

    List<IssueDTO> getAllIssueForUser(String companyId, Long communityId, Long userId);

    IssueDTO createNewIssue(String companyId, IssueDTO issueDTO);

    IssueDTO updateIssueStatus(String companyId, IssueStatusDTO status);
}
