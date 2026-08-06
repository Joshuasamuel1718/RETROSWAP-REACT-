package com.retroswap.RetroSwap_Backend.Model.Order;

import com.retroswap.RetroSwap_Backend.Model.Product;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
