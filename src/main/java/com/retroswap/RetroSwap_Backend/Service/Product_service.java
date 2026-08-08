package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Model.ProductRequest;
import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Repository.Product_repo;
import com.retroswap.RetroSwap_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product_service {
    @Autowired
    Product_repo productRepo;
    @Autowired
    UserRepo userRepo;
    public List<Product> getproducts()
    {
        return  productRepo.findAll();
    }
    public Product getProductById(long id)
    {

        return productRepo.findById(id).orElse(null);
    }


    public void addProduct(ProductRequest request) {


        User user = userRepo.findByEmail(getEmail());

        Product product = new Product();

        product.setUser(user);
        product.setDetails(request.getDetails());
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setImage(request.getImage());
        product.setColor(request.getColor());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());

        productRepo.save(product);

    }

    private String getEmail()
    {

        return  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

    public Product updateProduct(long id,Product product) {
        Product product1=productRepo.findById(id).orElse(null);
        if(product1==null) {
            return null;
        }
        product.setId(id);
        productRepo.save(product);
        return product1;
    }
}
