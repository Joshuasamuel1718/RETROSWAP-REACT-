package com.retroswap.RetroSwap_Backend.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String details;
    private String description;
    private String name;
    private byte category;
    private String image;
    private String color;
    private int quantity;
    private double price;

    // getters and setters
}