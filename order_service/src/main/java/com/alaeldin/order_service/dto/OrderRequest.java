package com.alaeldin.order_service.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderRequest {
    private long id;
    private List<OrderLineItemDto> orderLineItemDto;
}