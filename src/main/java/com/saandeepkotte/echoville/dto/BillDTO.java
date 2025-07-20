package com.saandeepkotte.echoville.dto;

import com.saandeepkotte.echoville.utils.enums.BillStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long id;
    @NotNull(message = "Bill amount is required")
    private Double amount;
    @NotNull(message = "Bill due date is required")
    @Future(message = "Due date cannot be in the past")
    private LocalDateTime dueDate;
    @NotNull(message = "House is required")
    private Long houseId;
    @NotNull(message = "Community is required")
    private Long communityId;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
}
