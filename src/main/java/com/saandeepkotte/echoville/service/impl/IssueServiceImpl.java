package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.dto.IssueDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.model.Issue;
import com.saandeepkotte.echoville.repository.IssueRepository;
import com.saandeepkotte.echoville.service.IssueService;
import com.saandeepkotte.echoville.service.ValidationHelperService;
import com.saandeepkotte.echoville.utils.enums.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl extends BaseServiceImpl<Issue, Long> implements IssueService {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ValidationHelperService validationHelperService;

    @Override
    public List<IssueDTO> getAllIssuesForCommunity(String companyId, Long communityId) {
        validationHelperService.runCommunityValidation(companyId, communityId);
        List<Issue> issues = issueRepository.findByCommunityId(communityId);
        if(issues.isEmpty()) {
            throw new EchoException("No issues found");
        }
        return issues.stream().map(Issue::toDto).toList();
    }

    @Override
    public List<IssueDTO> getAllIssueForUser(String companyId, Long communityId, Long userId) {
        return List.of();
    }

    @Override
    public IssueDTO createNewIssue(String companyId, IssueDTO issueDTO) {
        validationHelperService.runUserCommunityValidation(companyId, issueDTO.getCommunityId(), issueDTO.getResidentId());
        if(issueDTO.getStatus() == null) {
            issueDTO.setStatus(IssueStatus.OPEN);
        }
        Issue issue = new Issue().toModel(issueDTO);
        issue = issueRepository.save(issue);
        return issue.toDto();
    }
}
