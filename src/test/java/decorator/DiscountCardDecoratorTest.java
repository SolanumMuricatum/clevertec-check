package decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.entities.BasicReceipt;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.decorator.DiscountCardDecorator;
import ru.clevertec.check.entities.Receipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DiscountCardDecoratorTest {

    @BeforeEach
    void setUp() {
        DAOWorker daoWorker = DAOWorker.getINSTANCE();
        daoWorker.setResultRootPath("./result.csv");
        DAOWorker.setURL("jdbc:postgresql://localhost:5432/check");
        DAOWorker.setUSERNAME("postgres");
        DAOWorker.setPASSWORD("pepino111");
    }

    @Test
    void testGetProductsWithValidDiscountCard() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 4, "Product 1", 10.0, 10, true, 0, 100));
        Receipt receipt = new Receipt(products, 4444, 10.0, 0, 10, 0, 0.3, "08.07.2024", "18:00:00");
        DiscountCardDecorator decorator = new DiscountCardDecorator(receipt);
        receipt.setProducts(decorator.getProducts());

        Assertions.assertEquals(5, receipt.getDiscountPercentage());
        Assertions.assertEquals(2.0, receipt.getProducts().get(0).getDiscount());
    }

    @Test
    void testGetProductsWithMyDiscountCard() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 4, "Product 1", 10.0, 10, true, 0, 100));
        Receipt receipt = new Receipt(products, 1234, 10.0, 0, 10, 0, 0.3, "08.07.2024", "18:00:00");
        DiscountCardDecorator decorator = new DiscountCardDecorator(receipt);
        receipt.setProducts(decorator.getProducts());

        Assertions.assertEquals(2, receipt.getDiscountPercentage());
        Assertions.assertEquals(0.8, receipt.getProducts().get(0).getDiscount());
    }

    @Test
    void testGetProductsWithoutDiscountCard() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 4, "Product 1", 10.0, 10, true, 0, 100));
        Receipt receipt = new Receipt(products, 0, 10.0, 0, 10, 0, 0.3, "08.07.2024", "18:00:00");
        DiscountCardDecorator decorator = new DiscountCardDecorator(receipt);
        receipt.setProducts(decorator.getProducts());

        Assertions.assertEquals(0, receipt.getDiscountPercentage());
        Assertions.assertEquals(0, receipt.getProducts().get(0).getDiscount());
    }
}