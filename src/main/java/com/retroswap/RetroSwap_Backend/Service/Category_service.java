package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Repository.Product_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Category_service {

    @Autowired
    Product_repo productRepo;
    public List<Product> getByCategory(byte id)
    {
        return productRepo.findByCategory(id);
    }

}
