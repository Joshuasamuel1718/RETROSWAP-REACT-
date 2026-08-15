package com.retroswap.RetroSwap_Backend.Repository;

import com.retroswap.RetroSwap_Backend.Model.Order.Order;
import com.retroswap.RetroSwap_Backend.Model.Order.OrderItem;
import com.retroswap.RetroSwap_Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_repo extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
}
