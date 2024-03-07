package com.alaeldin.order_service.service;

import com.alaeldin.order_service.dto.OrderRequest;
import com.alaeldin.order_service.dto.OrderResponse;
import org.springframework.data.domain.Page;

public interface OrderService {

     void placeHolder(OrderRequest orderRequest);
     Page<OrderResponse> getAllOrders(int numberPage,int pageSize);
}
