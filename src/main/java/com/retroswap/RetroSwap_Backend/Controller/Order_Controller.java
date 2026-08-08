package com.retroswap.RetroSwap_Backend.Controller;

import com.retroswap.RetroSwap_Backend.Model.Order.Order;
import com.retroswap.RetroSwap_Backend.Model.Order.OrderItem;
import com.retroswap.RetroSwap_Backend.Service.Order_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/retroswap")
@RestController
public class Order_Controller {
    @Autowired
    Order_service orderService;
    @GetMapping("/orders")
    public ResponseEntity< List<Order>> getOrders()
    {
       return ResponseEntity.ok(orderService.getOrders());
    }
    @PostMapping("/orders/{option}")
    public ResponseEntity<Void> addOrder(@PathVariable byte option)
    {
        orderService.addOrder(option);
        return ResponseEntity.ok().build();
    }



}
