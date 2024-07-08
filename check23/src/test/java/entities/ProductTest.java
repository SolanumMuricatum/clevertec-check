package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.entities.Product;

class ProductTest {

    @Test
    void testConstructor() {
        int id = 1;
        int quantity = 5;
        String description = "Test Product";
        double price = 9.99;
        int quantity_in_stock = 10;
        boolean wholesale_product = true;
        double discount = 0.2;
        double totalPrice = 7.99;

        Product product = new Product(id, quantity, description, price, quantity_in_stock, wholesale_product, discount, totalPrice);

        Assertions.assertEquals(id, product.getId());
        Assertions.assertEquals(quantity, product.getQuantity());
        Assertions.assertEquals(description, product.getDescription());
        Assertions.assertEquals(price, product.getPrice());
        Assertions.assertEquals(quantity_in_stock, product.getQuantity_in_stock());
        Assertions.assertTrue(product.isWholesale_product());
        Assertions.assertEquals(discount, product.getDiscount());
        Assertions.assertEquals(totalPrice, product.getTotalPrice());
    }

    @Test
    void testSetters() {
        Product product = new Product();

        int id = 2;
        int quantity = 3;
        String description = "Another Test Product";
        double price = 14.99;
        int quantity_in_stock = 20;
        boolean wholesale_product = false;
        double discount = 0.1;
        double totalPrice = 13.49;

        product.setId(id);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity_in_stock(quantity_in_stock);
        product.setWholesale_product(wholesale_product);
        product.setDiscount(discount);
        product.setTotalPrice(totalPrice);

        Assertions.assertEquals(id, product.getId());
        Assertions.assertEquals(quantity, product.getQuantity());
        Assertions.assertEquals(description, product.getDescription());
        Assertions.assertEquals(price, product.getPrice());
        Assertions.assertEquals(quantity_in_stock, product.getQuantity_in_stock());
        Assertions.assertFalse(product.isWholesale_product());
        Assertions.assertEquals(discount, product.getDiscount());
        Assertions.assertEquals(totalPrice, product.getTotalPrice());
    }
}