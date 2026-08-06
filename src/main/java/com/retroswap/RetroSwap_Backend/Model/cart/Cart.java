package com.retroswap.RetroSwap_Backend.Model.cart;

import com.retroswap.RetroSwap_Backend.Model.User;
import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long cartId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
