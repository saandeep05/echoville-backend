package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.CommunityDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    private String name;
    private String location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<House> houses = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<EchoUser> users = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Issue> issues = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();

    public CommunityDTO toDto() {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(id);
        communityDTO.setName(name);
        communityDTO.setLocation(location);
        communityDTO.setCompanyId(company.getId());
        communityDTO.setHouses(houses.stream().map(House::getId).toList());
        communityDTO.setUsers(users.stream().map(EchoUser::getId).toList());
        communityDTO.setPosts(posts.stream().map(Post::getId).toList());
        communityDTO.setIssues(issues.stream().map(Issue::getId).toList());
        communityDTO.setBills(bills.stream().map(Bill::getId).toList());
        return communityDTO;
    }
}

