package com.retroswap.RetroSwap_Backend.Controller;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Service.Category_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/retroswap")
@RestController
public class Category_Controller {
    @Autowired
    Category_service categoryService;
    @GetMapping("/product/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable byte id)
    {
        List<Product> products=categoryService.getByCategory(id);
        if(products.size()==0)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(products);
    }

}
