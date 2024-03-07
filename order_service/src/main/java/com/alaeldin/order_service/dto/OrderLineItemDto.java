package com.alaeldin.order_service.dto;


import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderLineItemDto {
    private long id;
    private String skuCode;
    private BigDecimal price;
    private int quantity;
}
