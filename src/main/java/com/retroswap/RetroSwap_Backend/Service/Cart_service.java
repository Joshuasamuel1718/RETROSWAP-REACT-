package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.AddCartRequest;
import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Model.cart.Cart;
import com.retroswap.RetroSwap_Backend.Model.cart.CartItem;
import com.retroswap.RetroSwap_Backend.Repository.Cart_itemrepo;
import com.retroswap.RetroSwap_Backend.Repository.Cart_repo;
import com.retroswap.RetroSwap_Backend.Repository.Product_repo;
import com.retroswap.RetroSwap_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cart_service {
    @Autowired
    Cart_repo cartRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    Cart_itemrepo cartItemrepo;
    @Autowired
    Product_repo productRepo;
    public List<CartItem> displayCart() {
        String email=getEmail();
        User user=userRepo.findByEmail(email);
        Cart cart=cartRepo.findByUser(user);
        if(cart==null)
        {
            return List.of();
        }
        return cartItemrepo.findByCart(cart);

    }
    private String getEmail()
    {
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return email;
    }

    public void addCart(AddCartRequest addCartRequest) {
        User user=userRepo.findByEmail(getEmail());
        Cart cart=cartRepo.findByUser(user);
        if(cart==null)
        {
            cart=new Cart();
            cart.setUser(user);
            cart=cartRepo.save(cart);
        }
        Product product=productRepo.findById(addCartRequest.getProductId()).orElse(null);
        CartItem cartItem1=new CartItem();
        cartItem1.setCart(cart);
        cartItem1.setProduct(product);
        cartItem1.setQuantity(addCartRequest.getQuantity());
        cartItemrepo.save(cartItem1);
    }

    public void updateCart(CartItem cartItem) {
        User user=userRepo.findByEmail(getEmail());
        Cart cart=cartRepo.findByUser(user);
        List<CartItem> cartItem1=cartItemrepo.findByCart(cart);
        for (int i=0;i<cartItem1.size();i++)
        {
            if(cartItem.getId()==cartItem1.get(i).getId())
            {
                cartItem1.get(i).setQuantity(cartItem.getQuantity());
                cartItemrepo.save(cartItem1.get(i));
                break;
            }
        }


    }
    public void deleteCart(long id)
    {
        User user=userRepo.findByEmail(getEmail());
        Cart cart=cartRepo.findByUser(user);
        List<CartItem> cartItem1=cartItemrepo.findByCart(cart);
        for (int i=0;i<cartItem1.size();i++)
        {
            if(id==cartItem1.get(i).getId())
            {
                cartItemrepo.delete(cartItem1.get(i));
                break;
            }
        }

    }

}
