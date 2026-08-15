package com.retroswap.RetroSwap_Backend.Controller;

import com.retroswap.RetroSwap_Backend.Model.cart.AddCartRequest;
import com.retroswap.RetroSwap_Backend.Model.cart.CartItem;
import com.retroswap.RetroSwap_Backend.Model.cart.UpdateCart;
import com.retroswap.RetroSwap_Backend.Service.Cart_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/retroswap")
@RestController
public class Cart_controller {
    @Autowired
    Cart_service cartService;
    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> displayCart()
    {
        return ResponseEntity.ok(cartService.displayCart());
    }
    @PostMapping("/cart")
    public ResponseEntity<Void> addCart(@RequestBody AddCartRequest addCartRequest)
    {
       cartService.addCart(addCartRequest);
       return ResponseEntity.ok().build();

    }
    @PutMapping("/cart")
    public  ResponseEntity<Void> updateCart(@RequestBody UpdateCart updateCart)
    {
        cartService.updateCart(updateCart);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable long id)
    {
        cartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }



}
