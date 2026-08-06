package com.retroswap.RetroSwap_Backend.Model.Order;

import com.retroswap.RetroSwap_Backend.Model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime orderDate;
    private double amount;
    private byte option;
    private boolean status;


}
