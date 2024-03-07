package com.alaeldin.order_service.mapper;

import com.alaeldin.order_service.dto.OrderResponse;
import com.alaeldin.order_service.model.Order;

public class MapperResponse {

    public static OrderResponse mapToOrderDto(Order order){
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setOsrderLineItemDtoList(order.getOrderLineItems());

        return orderResponse;
    }

    public static Order mapToOrder(OrderResponse orderResponse){

        Order order = new Order();
        order.setOrderNumber(orderResponse.getOrderNumber());
        order.setOrderLineItems(orderResponse.getOsrderLineItemDtoList());

        return order;
    }
}
