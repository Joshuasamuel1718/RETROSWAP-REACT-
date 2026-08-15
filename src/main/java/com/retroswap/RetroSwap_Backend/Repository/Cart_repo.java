package com.retroswap.RetroSwap_Backend.Repository;

import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cart_repo extends JpaRepository<Cart,Long> {
    Cart findByUser(User user);
}
