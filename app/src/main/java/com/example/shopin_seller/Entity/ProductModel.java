package com.example.shopin_seller.Entity;

import java.util.ArrayList;

public class ProductModel {
    private String productId;
    private String sellerId;

    private String productName;
    private String category;
    private String brandName;

    private double price;
    private double discountPrice;

    private int stock;

    private String description;

    private String productImage;

    private String createdDate;
    private String createdTime;

    private boolean inStock;
    private boolean featured;

    public ProductModel() {
    }

    public ProductModel(String productId, String sellerId, String productName, String category, String brandName, double price, double discountPrice, int stock, String description, String productImage, String createdDate, String createdTime, boolean inStock, boolean featured) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.category = category;
        this.brandName = brandName;
        this.price = price;
        this.discountPrice = discountPrice;
        this.stock = stock;
        this.description = description;
        this.productImage = productImage;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.inStock = inStock;
        this.featured = featured;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
