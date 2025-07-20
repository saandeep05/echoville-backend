package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.HouseDTO;
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
public class House extends BaseEntity<House, HouseDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    private String block;
    private String floor;
    private String number;
    private String type; // 2BHK, etc.

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<EchoUser> residents = new ArrayList<>();

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();

    public House(HouseDTO houseDTO) {
        this.toModel(houseDTO);
    }

    public HouseDTO toDto() {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId(id);
        houseDTO.setNumber(number);
        houseDTO.setFloor(floor);
        houseDTO.setBlock(block);
        houseDTO.setType(type);
        houseDTO.setCommunityId(community.getId());
        houseDTO.setResidents(residents.stream().map(EchoUser::getId).toList());
        houseDTO.setBills(bills.stream().map(Bill::getId).toList());
        return houseDTO;
    }

    @Override
    public House toModel(HouseDTO houseDTO) {
        this.setId(houseDTO.getId());
        this.setNumber(houseDTO.getNumber());
        this.setFloor(houseDTO.getFloor());
        this.setBlock(houseDTO.getBlock());
        this.setType(houseDTO.getType());
        if(houseDTO.getCommunityId() != null) {
            Community community = new Community();
            community.setId(houseDTO.getCommunityId());
            this.setCommunity(community);
        }
        return this;
    }
}

