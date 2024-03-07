/**package com.alaeldin.order_service.mapper;

import com.alaeldin.order_service.dto.OrderRequest;
import com.alaeldin.order_service.model.Order;

public class OrderMapper {

    public static OrderRequest mapToOrderDto(Order order){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderLineItemDto(order.getOrderLineItems());

        return orderRequest;
    }

    public static Order mapToOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setOrderLineItems(orderRequest.getOrderLineItemsDto());

        return order;
    }
}**/
