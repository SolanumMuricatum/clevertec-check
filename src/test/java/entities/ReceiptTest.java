package entities;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.entities.Receipt;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {

    @Test
    void testReceiptCreation() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, 4, "Product 1", 10.0, 10, true, 0.78, 40);
        Product product2 = new Product(1, 4, "Product 2", 10.0, 10, true, 0.78, 40);
        products.add(product1);
        products.add(product2);
        int discountCard = 123456;
        double balanceDebitCard = 100.0;
        int discountPercentage = 10;
        double totalPrice = 50.0;
        double totalDiscount = 5.0;
        double totalPriceWithDiscount = 45.0;
        String date = "2024-07-08";
        String time = "12:34:56";

        Receipt receipt = new Receipt(products, discountCard, balanceDebitCard, discountPercentage, totalPrice, totalDiscount, totalPriceWithDiscount, date, time);

        assertEquals(products, receipt.getProducts());
        assertEquals(discountCard, receipt.getDiscountCard());
        assertEquals(balanceDebitCard, receipt.getBalanceDebitCard());
        assertEquals(discountPercentage, receipt.getDiscountPercentage());
        assertEquals(totalPrice, receipt.getTotalPrice());
        assertEquals(totalDiscount, receipt.getTotalDiscount());
        assertEquals(totalPriceWithDiscount, receipt.getTotalPriceWithDiscount());
        assertEquals(date, receipt.getDate());
        assertEquals(time, receipt.getTime());
    }

}