package com.retroswap.RetroSwap_Backend.Model.Order;

import com.retroswap.RetroSwap_Backend.Model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity

@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime orderDate;
    private double amount;
    private byte paymentoption;
    private boolean status;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
