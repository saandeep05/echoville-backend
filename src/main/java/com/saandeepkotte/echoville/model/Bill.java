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

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Override
    public BillDTO toDto() {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(id);
        billDTO.setAmount(amount);
        billDTO.setDueDate(dueDate);
        billDTO.setStatus(status);
        billDTO.setHouseId(house.getId());
        billDTO.setCommunityId(community.getId());
        return billDTO;
    }

    @Override
    public Bill toModel(BillDTO billDTO) {
        this.setId(billDTO.getId());
        this.setAmount(billDTO.getAmount());
        this.setDueDate(billDTO.getDueDate());
        this.setStatus(billDTO.getStatus());
        return this;
    }
}

