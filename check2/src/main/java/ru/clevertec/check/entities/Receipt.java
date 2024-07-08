package main.java.ru.clevertec.check.entities;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Receipt implements BasicReceipt {  //чек мейкер
    //лист с продуктами
    //дата и время
    //инфа о дисконтке
    //финальная стоимость

    private List<Product> products;
    private int discountCard;
    private double balanceDebitCard;
    private int discountPercentage;
    private double totalPrice;
    private double totalDiscount;
    private double totalPriceWithDiscount;
    private String date;
    private String time;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(int  discountCard) {
        this.discountCard = discountCard;
    }

    public double getBalanceDebitCard() {
        return balanceDebitCard;
    }

    public void setBalanceDebitCard(Double balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalPriceWithDiscount() {
        return totalPriceWithDiscount;
    }

    public void setTotalPriceWithDiscount(double totalPriceWithDiscount) {
        this.totalPriceWithDiscount = totalPriceWithDiscount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void printReceipt( BasicReceipt basicReceipt, char separator, String currency){
        System.out.println("Чек успешно создан\n");
        System.out.println("Date" + separator + "Time\n" +
                basicReceipt.getDate() + separator + basicReceipt.getTime() + "\n\n" +
                "QTY" + separator + "DESCRIPTION" + separator + "PRICE" + separator + "DISCOUNT" + separator + "TOTAL");
        for (Product product: basicReceipt.getProducts()){
            System.out.println(String.valueOf(product.getQuantity()) + separator + product.getDescription() + separator + product.getPrice() + currency + separator + product.getDiscount() + currency + separator + product.getTotalPrice() + currency);
        }
        System.out.println();
        if (basicReceipt.getDiscountCard()!= 0){
            System.out.println("DISCOUNT CARD" + separator + "DISCOUNT PERCENTAGE");
            System.out.println( String.valueOf(basicReceipt.getDiscountCard()) + separator + basicReceipt.getDiscountPercentage() + "%\n");
        }
        System.out.println("TOTAL PRICE" + separator + "TOTAL DISCOUNT" + separator + "TOTAL WITH DISCOUNT\n" +
                basicReceipt.getTotalPrice() + currency + separator + basicReceipt.getTotalDiscount() + currency + separator + basicReceipt.getTotalPriceWithDiscount() + currency);
    }
}
