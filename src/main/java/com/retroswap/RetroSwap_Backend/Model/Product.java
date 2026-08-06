package com.retroswap.RetroSwap_Backend.Model;

import jakarta.persistence.*;

@Table
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long sellerid;
    private String details;
    private String description;
    private String name;
    private byte category;
    private String image;
    private String color;
    private int quantity;
    private double price;

    public long getId() {
        return id;
    }

    public long getSellerid() {
        return sellerid;
    }

    public String getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public byte getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSellerid(long sellerid) {
        this.sellerid = sellerid;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
