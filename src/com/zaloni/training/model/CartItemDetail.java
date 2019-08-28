package com.zaloni.training.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaloni.training.entity.CartItem;
import com.zaloni.training.entity.Product;
import com.zaloni.training.service.ProductService;

@Component
public class CartItemDetail {

    @Autowired
    private ProductService productService;

    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private int productPrice;
    private int amount;
    private boolean isAvailable;

    public CartItemDetail () {
        
    }

    public CartItemDetail (CartItem item, Product product) {
        this.id = item.getId();
        this.productId = item.getProductId();
        this.quantity = item.getQuantity().intValue();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.amount = this.productPrice * this.quantity;
        this.isAvailable = (product.getStock() - this.quantity) > 0;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public boolean getAvailable() {
        return this.isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
