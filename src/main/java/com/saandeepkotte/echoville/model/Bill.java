package com.saandeepkotte.echoville.model;

import com.saandeepkotte.echoville.dto.BillDTO;
import com.saandeepkotte.echoville.utils.enums.BillStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends BaseEntity<Bill, BillDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    private double amount;
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private BillStatus status;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    public Bill(BillDTO billDTO) {
        this.toModel(billDTO);
    }

    @Override
    public BillDTO toDto() {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(id);
        billDTO.setAmount(amount);
        billDTO.setDueDate(dueDate);
        billDTO.setStatus(status);
        billDTO.setHouseId(house.getId());
        billDTO.setCommunityId(community.getId());
        billDTO.setTitle(title);
        billDTO.setDescription(description);
        return billDTO;
    }

    @Override
    public Bill toModel(BillDTO billDTO) {
        this.setId(billDTO.getId());
        this.setAmount(billDTO.getAmount());
        this.setDueDate(billDTO.getDueDate());
        this.setStatus(billDTO.getStatus());
        this.setTitle(billDTO.getTitle());
        this.setDescription(billDTO.getDescription());
        if(billDTO.getHouseId() != null) {
            House house = new House();
            house.setId(billDTO.getHouseId());
            this.setHouse(house);
        }
        if(billDTO.getCommunityId() != null) {
            Community community = new Community();
            community.setId(billDTO.getCommunityId());
            this.setCommunity(community);
        }
        return this;
    }
}

