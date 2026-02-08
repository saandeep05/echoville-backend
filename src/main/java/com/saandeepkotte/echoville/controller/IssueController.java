package com.saandeepkotte.echoville.controller;

import com.saandeepkotte.echoville.controller.helper.RestControllerHelper;
import com.saandeepkotte.echoville.dto.EntityDTO;
import com.saandeepkotte.echoville.dto.IssueDTO;
import com.saandeepkotte.echoville.dto.IssueStatusDTO;
import com.saandeepkotte.echoville.exception.EchoException;
import com.saandeepkotte.echoville.service.IssueService;
import com.saandeepkotte.echoville.utils.enums.IssueStatus;
import com.saandeepkotte.echoville.utils.urls.RequestPathURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @GetMapping(RequestPathURLs.FOR_COMMUNITY)
    public ResponseEntity<EntityDTO<List<IssueDTO>>> getAllIssuesForCommunity(@RequestHeader("companyId") String companyId,
                                                                              @PathVariable("communityId") Long communityId) {
        EntityDTO<List<IssueDTO>> entityDTO = null;
        try {
            List<IssueDTO> issueDTOList = issueService.getAllIssuesForCommunity(companyId, communityId);
            entityDTO = RestControllerHelper.getResponseEntity(issueDTOList, null);
        } catch (EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @GetMapping(RequestPathURLs.USER_ISSUE)
    public ResponseEntity<EntityDTO<List<IssueDTO>>> getAllIssuesForUser(@RequestHeader("companyId") String companyId,
                                                                         @RequestHeader("communityId") Long communityId,
                                                                         @PathVariable("userId") Long userId) {
        EntityDTO<List<IssueDTO>> entityDTO = null;
        try {
            List<IssueDTO> issueDTOList = issueService.getAllIssueForUser(companyId, communityId, userId);
            entityDTO = RestControllerHelper.getResponseEntity(issueDTOList, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityDTO<IssueDTO>> createIssue(@RequestHeader("companyId") String companyId,
                                                           @Valid @RequestBody IssueDTO issueDTO) {
        EntityDTO<IssueDTO> entityDTO = null;
        try {
            issueDTO = issueService.createNewIssue(companyId, issueDTO);
            entityDTO = RestControllerHelper.getResponseEntity(issueDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    @PutMapping(RequestPathURLs.UPDATE_ISSUE_STATUS)
    public ResponseEntity<EntityDTO<IssueDTO>> updateStatus(@RequestHeader("companyId") String companyId,
                                                            @RequestBody IssueStatusDTO issueStatusDTO) {
        EntityDTO<IssueDTO> entityDTO = null;
        try {
            IssueDTO issueDTO = issueService.updateIssueStatus(companyId, issueStatusDTO);
            entityDTO = RestControllerHelper.getResponseEntity(issueDTO, null);
        } catch(EchoException e) {
            entityDTO = RestControllerHelper.getResponseEntity(null, e.getMessage());
        }
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
