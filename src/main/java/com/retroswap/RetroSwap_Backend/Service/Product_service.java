package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Repository.Product_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product_service {
    @Autowired
    Product_repo productRepo;
    public List<Product> getproducts()
    {
        return  productRepo.findAll();
    }
    public Product getProductById(long id)
    {
        return productRepo.findById(id).orElse(null);
    }


    public void addProduct(Product product) {

        productRepo.save(product);

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
