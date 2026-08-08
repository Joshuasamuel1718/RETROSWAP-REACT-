package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.Order.Order;
import com.retroswap.RetroSwap_Backend.Model.Order.OrderItem;
import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Model.cart.Cart;
import com.retroswap.RetroSwap_Backend.Model.cart.CartItem;
import com.retroswap.RetroSwap_Backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class Order_service {
    @Autowired
    OrderItem_repo orderItemRepo;
    @Autowired
    Order_repo orderRepo;
    @Autowired
    Product_service productService;
    @Autowired
    Cart_itemrepo cartItemrepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    Cart_repo cartRepo;
    public List<Order> getOrders() {
        User user=userRepo.findByEmail(getEmail());
        return orderRepo.findByUser(user);
    }
    public void addOrder(byte userOption)
    {
        Order order=new Order();
        List<OrderItem> orderItems=new ArrayList<>();
        User user=userRepo.findByEmail(getEmail());
        Cart cart=cartRepo.findByUser(user);
        List<CartItem> cartItems=cartItemrepo.findByCart(cart);
        order.setStatus(false);
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentoption(userOption);
        double amount=0;
        for (int i = 0; i<cartItems.size(); i++){
            CartItem currCart=cartItems.get(i);
            amount+=(currCart.getProduct().getPrice()*currCart.getQuantity());
        }
        order.setAmount(amount);
        order=orderRepo.save(order);
        for (int i = 0; i<cartItems.size(); i++) {

            CartItem currCart=cartItems.get(i);
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(currCart.getProduct());
            orderItem.setQuantity(currCart.getQuantity());
            orderItemRepo.save(orderItem);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        for (CartItem cartItem : cartItems) {
            cartItemrepo.delete(cartItem);
        }

    }

    private String getEmail()
    {
        return  SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    }

}
