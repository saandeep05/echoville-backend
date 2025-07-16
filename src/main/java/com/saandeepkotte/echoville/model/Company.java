package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.CompanyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity<Company, CompanyDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "company_id")
    private String id;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Community> communities = new ArrayList<>();

    @Override
    public CompanyDTO toDto() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(id);
        companyDTO.setName(name);
        companyDTO.setCommunityId(communities.stream().map(Community::getId).toList());
        return companyDTO;
    }

    @Override
    public Company toModel(CompanyDTO companyDTO) {
        this.setId(companyDTO.getId());
        this.setName(companyDTO.getName());
        return this;
    }
}

