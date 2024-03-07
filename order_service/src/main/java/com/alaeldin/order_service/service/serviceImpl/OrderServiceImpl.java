package com.alaeldin.order_service.service.serviceImpl;

import com.alaeldin.order_service.dto.OrderLineItemDto;
import com.alaeldin.order_service.dto.OrderRequest;
import com.alaeldin.order_service.dto.OrderResponse;
import com.alaeldin.order_service.mapper.MapperOrderLineItemDto;
import com.alaeldin.order_service.mapper.MapperResponse;
import com.alaeldin.order_service.model.Order;
import com.alaeldin.order_service.model.OrderLineItem;
import com.alaeldin.order_service.repository.OrderRepository;
import com.alaeldin.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
   private final OrderRepository orderRepository;
    @Override
    public void placeHolder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDto()
                .stream().map(MapperOrderLineItemDto::mapToOrderLineItem).toList();
        order.setOrderLineItems(orderLineItems);
        Order SaveOrder =  orderRepository.save(order);
   }

    @Override
    public Page<OrderResponse> getAllOrders(int numberPage,int pageSize) {
        Pageable pageable = PageRequest.of(numberPage,pageSize);
        Page<Order> order = orderRepository.findAll(pageable);
        return order.map(MapperResponse::mapToOrderDto);

    }


}
