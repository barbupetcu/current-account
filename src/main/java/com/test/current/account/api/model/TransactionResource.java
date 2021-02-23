package com.test.current.account.api.model;

import com.test.current.account.infrastructure.commons.CustomJsonSerializable;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResource implements CustomJsonSerializable {
    private BigDecimal amount;
    private String description;
    private LocalDateTime occurredAt;
    private TransactionType type;
}