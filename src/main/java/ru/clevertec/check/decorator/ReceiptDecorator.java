package main.java.ru.clevertec.check.decorator;

import main.java.ru.clevertec.check.entities.BasicReceipt;
import main.java.ru.clevertec.check.entities.Product;

import java.text.SimpleDateFormat;
import java.util.List;

public abstract class ReceiptDecorator implements BasicReceipt { //базовый декоратор

    protected final BasicReceipt basicReceipt;
    public ReceiptDecorator(BasicReceipt basicReceipt) {
        this.basicReceipt = basicReceipt;
    }

    @Override
    public List<Product> getProducts() {
        return basicReceipt.getProducts();
    }

    @Override
    public int getDiscountCard() {
        return basicReceipt.getDiscountCard();
    }

    @Override
    public void setDiscountPercentage(int discountPercentage){
        basicReceipt.setDiscountPercentage(discountPercentage);
    }

    @Override
    public void setTotalPrice(double totalPrice) {
        basicReceipt.setTotalPrice(totalPrice);
    }

    @Override
    public void setTotalDiscount(double discount) {
        basicReceipt.setTotalDiscount(discount);
    }

    @Override
    public void setTotalPriceWithDiscount(double totalPriceWithDiscount) {
        basicReceipt.setTotalPriceWithDiscount(totalPriceWithDiscount);
    }

    @Override
    public double getTotalPrice() {
        return basicReceipt.getTotalPrice();
    }

    @Override
    public double getTotalDiscount() {
        return basicReceipt.getTotalDiscount();
    }

    @Override
    public int getDiscountPercentage() {
        return basicReceipt.getDiscountPercentage();
    }

    @Override
    public double getTotalPriceWithDiscount() {
        return basicReceipt.getTotalPriceWithDiscount();
    }

    @Override
    public String getDate() {
        return basicReceipt.getDate();
    }

    @Override
    public String getTime() {
        return basicReceipt.getTime();
    }

    @Override
    public void setDate(String simpleDateFormat) {
        basicReceipt.setDate(simpleDateFormat);
    }

    @Override
    public void setTime(String simpleDateFormat) {
        basicReceipt.setTime(simpleDateFormat);
    }

    @Override
    public void printReceipt(BasicReceipt basicReceipt, char separator, String currency) {
        basicReceipt.printReceipt(basicReceipt, separator, currency);
    }
    @Override
    public double getBalanceDebitCard() {
        return basicReceipt.getBalanceDebitCard();
    }
}
