package com.alaeldin.order_service.mapper;

import com.alaeldin.order_service.dto.OrderLineItemDto;
import com.alaeldin.order_service.model.OrderLineItem;

public class MapperOrderLineItemDto {

    public static OrderLineItemDto mapToOrderItemLineDto(OrderLineItem orderLineItem){

        OrderLineItemDto orderLineItemDto = new OrderLineItemDto();
        orderLineItemDto.setSkuCode(orderLineItem.getSkuCode());
        orderLineItemDto.setQuantity(orderLineItem.getQuantity());
        orderLineItemDto.setPrice(orderLineItem.getPrice());

        return orderLineItemDto;
    }

    public static OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto){

        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setPrice(orderLineItemDto.getPrice());

        return orderLineItem;

    }
}
