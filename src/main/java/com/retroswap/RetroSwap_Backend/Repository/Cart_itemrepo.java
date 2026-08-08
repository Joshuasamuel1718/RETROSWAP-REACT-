package com.retroswap.RetroSwap_Backend.Repository;

import com.retroswap.RetroSwap_Backend.Model.cart.Cart;
import com.retroswap.RetroSwap_Backend.Model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Cart_itemrepo extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCart(Cart cart);
}
