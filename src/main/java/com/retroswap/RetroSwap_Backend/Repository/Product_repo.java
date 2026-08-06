package com.retroswap.RetroSwap_Backend.Repository;

import com.retroswap.RetroSwap_Backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_repo extends JpaRepository<Product,Long>{
}
