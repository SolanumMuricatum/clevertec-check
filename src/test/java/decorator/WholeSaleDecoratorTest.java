package decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.decorator.DiscountCardDecorator;
import ru.clevertec.check.decorator.WholeSaleDecorator;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.entities.Receipt;

import java.util.ArrayList;
import java.util.List;

public class WholeSaleDecoratorTest {

    @Test
    void testGetWholeSaleProductDiscount() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 10, "Product 1", 10.0, 20, true, 0, 100));
        Receipt receipt = new Receipt(products, 4444, 100.0, 0, 10, 0, 0.3, "08.07.2024", "18:00:00");
        WholeSaleDecorator decorator = new WholeSaleDecorator(receipt);
        receipt.setProducts(decorator.getProducts());

        Assertions.assertEquals(10.0, receipt.getProducts().get(0).getDiscount());
    }
}
