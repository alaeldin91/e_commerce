package com.alaeldin.order_service.controller;


import com.alaeldin.order_service.dto.OrderRequest;
import com.alaeldin.order_service.dto.OrderResponse;
import com.alaeldin.order_service.repository.OrderRepository;
import com.alaeldin.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/place-holder")
    public ResponseEntity<String> placeHolder(@RequestBody OrderRequest orderRequest){

        orderService.placeHolder(orderRequest);

        return new ResponseEntity<>("Save Order Successfully", HttpStatus.OK);
    }

    @GetMapping(value = "get-all-orders",params = {"pageNumber","pageSize"})
    public ResponseEntity<Page<OrderResponse>> getAllOrders(@RequestParam("pageNumber") int pageNumber
                                                     ,@RequestParam("pageSize")int pageSize){
        Page<OrderResponse> orderResponses = orderService.getAllOrders(pageNumber,pageSize);

        return new ResponseEntity<>(orderResponses,HttpStatus.OK);

    }


}
