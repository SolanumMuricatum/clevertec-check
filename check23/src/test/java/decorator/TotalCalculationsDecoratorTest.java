package decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.decorator.DiscountCardDecorator;
import ru.clevertec.check.decorator.TotalCalculationsDecorator;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.entities.Receipt;

import java.util.ArrayList;
import java.util.List;

public class TotalCalculationsDecoratorTest {

    @Test
    void testGetProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 4, "Product 1", 10.0, 10, true, 0.78, 40));
        products.add(new Product(1, 4, "Product 2", 10.0, 10, true, 0.78, 40));
        Receipt receipt = new Receipt(products, 1234, 1000.0, 3, 0, 0, 0, "08.07.2024", "18:00:00");

        TotalCalculationsDecorator decorator = new TotalCalculationsDecorator(receipt);
        receipt.setProducts(decorator.getProducts());

        Assertions.assertEquals(80, receipt.getTotalPrice());
        Assertions.assertEquals(1.56, receipt.getTotalDiscount());
        Assertions.assertEquals(78.44, receipt.getTotalPriceWithDiscount());
    }
}
