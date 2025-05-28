package ru.clevertec.check.entities;

import java.util.List;

public interface BasicReceipt { //абстрактный чек
    public List<Product> getProducts();
    public int getDiscountCard();
    public void setDiscountPercentage(int discountPercentage);
    public void setTotalPrice(double totalPrice);
    public void setTotalDiscount(double discount);
    public void setTotalPriceWithDiscount(double totalPriceWithDiscount);
    public double getTotalPrice();
    public double getTotalDiscount();
    public int getDiscountPercentage();
    public double getTotalPriceWithDiscount();
    public String getDate();
    public String getTime();
    public void setDate(String simpleDateFormat);
    public void setTime(String simpleDateFormat);
    public void printReceipt( BasicReceipt basicReceipt, char separator, String currency);
    public double getBalanceDebitCard();

}
