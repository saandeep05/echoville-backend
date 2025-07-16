package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.IssueDTO;
import com.saandeepkotte.echoville.utils.enums.IssueStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue extends BaseEntity<Issue, IssueDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "raised_by")
    private EchoUser raisedBy;

    @Override
    public IssueDTO toDto() {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(id);
        issueDTO.setTitle(title);
        issueDTO.setDesctiption(description);
        issueDTO.setStatus(status);
        issueDTO.setResidentId(raisedBy.getId());
        issueDTO.setCommunityId(community.getId());
        return issueDTO;
    }

    @Override
    public Issue toModel(IssueDTO issueDTO) {
        this.setId(issueDTO.getId());
        this.setTitle(issueDTO.getTitle());
        this.setDescription(issueDTO.getDesctiption());
        this.setStatus(issueDTO.getStatus());
        return this;
    }
}

