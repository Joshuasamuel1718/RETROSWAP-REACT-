package com.retroswap.RetroSwap_Backend.Model.cart;

import com.retroswap.RetroSwap_Backend.Model.User;
import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long cartId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCartId() {
        return cartId;
    }

    public User getUser() {
        return user;
    }
}
