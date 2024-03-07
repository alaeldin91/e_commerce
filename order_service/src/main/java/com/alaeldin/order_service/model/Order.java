package com.alaeldin.order_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity()
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem>OrderLineItems;

}
