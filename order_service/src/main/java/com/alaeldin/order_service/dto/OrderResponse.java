package com.alaeldin.order_service.dto;

import com.alaeldin.order_service.model.OrderLineItem;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
private String orderNumber;
private List<OrderLineItem> osrderLineItemDtoList;
}
