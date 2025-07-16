package com.saandeepkotte.echoville.dto;

import com.saandeepkotte.echoville.utils.enums.BillStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long id;
    @NotBlank(message = "Bill amount is required")
    private double amount;
    @NotBlank(message = "Bill due date is required")
    private LocalDateTime dueDate;
    @NotBlank(message = "House is required")
    private Long houseId;
    @NotBlank(message = "Community is required")
    private Long communityId;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
}
