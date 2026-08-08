package com.retroswap.RetroSwap_Backend.Controller;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Model.ProductRequest;
import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Service.Product_service;
import com.retroswap.RetroSwap_Backend.Service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/retroswap")
@RestController
public class Product_Controller {
    @Autowired
    Product_service productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>  getproducts()
    {
        List<Product> productList=productService.getproducts();
        if(productList.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productList);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id)
    {
       Product product= productService.getProductById(id);
       if(product==null)
       {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(product);
    }
    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequest request) {


        if(request==null)
        {
            return ResponseEntity.badRequest().build();
        }
        productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable long id, @RequestBody Product product)
    {
        Product product1=productService.updateProduct(id,product);
            if(product1==null)
            {
                return ResponseEntity.badRequest().build();
            }
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
