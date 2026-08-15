package com.retroswap.RetroSwap_Backend.Repository;

import com.retroswap.RetroSwap_Backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_repo extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory(byte category);
}
