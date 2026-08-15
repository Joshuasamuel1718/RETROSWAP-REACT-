package com.retroswap.RetroSwap_Backend.Model.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retroswap.RetroSwap_Backend.Model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
