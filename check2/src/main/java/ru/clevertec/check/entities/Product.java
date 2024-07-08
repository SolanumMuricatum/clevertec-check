package main.java.ru.clevertec.check.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product implements BasicProduct {
    private int id;
    private int quantity;
    private String description;
    private double price;
    private int quantity_in_stock;
    private boolean  wholesale_product;

    private double discount;
    private double totalPrice;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity_in_stock=" + quantity_in_stock +
                ", wholesale_product=" + wholesale_product +
                ", discount='" + discount + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public boolean isWholesale_product() {
        return wholesale_product;
    }

    public void setWholesale_product(boolean wholesale_product) {
        this.wholesale_product = wholesale_product;
    }
}
