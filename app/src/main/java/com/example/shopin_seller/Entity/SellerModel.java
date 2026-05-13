package com.example.shopin_seller.Entity;

public class SellerModel {

    private String sellerId;
    private String shopName;
    private String ownerName;
    private String industryType;
    private String mobileNumber;
    private String email;
    private String address;
    private String gstNumber;
    private String createdAt;

    // Empty Constructor Required for Firebase
    public SellerModel() {
    }

    // Constructor
    public SellerModel(String sellerId,
                       String shopName,
                       String ownerName,
                       String industryType,
                       String mobileNumber,
                       String email,
                       String address,
                       String gstNumber,

                       String createdAt) {

        this.sellerId = sellerId;
        this.shopName = shopName;
        this.ownerName = ownerName;
        this.industryType = industryType;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.gstNumber = gstNumber;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}