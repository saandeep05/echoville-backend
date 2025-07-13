package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.UserDTO;
import com.saandeepkotte.echoville.utils.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "echo_user")
public class EchoUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private UserRole role;
    private boolean isOwner;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // Only needed for COMPANY_ADMIN

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(mappedBy = "createdBy")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "raisedBy")
    private List<Issue> issues = new ArrayList<>();

    public UserDTO toDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(null);
        userDTO.setPhone(phone);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setCompanyId(company != null ? company.getId() : null);
        userDTO.setCommunityId(community != null ? community.getId() : null);
        userDTO.setHouseId(house != null ? house.getId() : null);
        userDTO.setIssueIds(issues.stream().map(Issue::getId).toList());
        userDTO.setPostId(posts.stream().map(Post::getId).toList());
        userDTO.setRole(role);
        return userDTO;
    }
}
