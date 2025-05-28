package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.entities.BasicReceipt;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.entities.Receipt;
import ru.clevertec.check.exception.IncorrectInputException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class DAOWorkerTest {
    private DAOWorker daoWorker;

    @BeforeEach
    void setUp() {
        daoWorker = DAOWorker.getINSTANCE();
        daoWorker.setResultRootPath("./result.csv");
        DAOWorker.setURL("jdbc:postgresql://localhost:5432/check");
        DAOWorker.setUSERNAME("postgres");
        DAOWorker.setPASSWORD("pepino111");
    }

    @Test
    void readProductTableTest() {
        Map<Integer, List<String>> productData = daoWorker.read("product");
        assertFalse(productData.isEmpty());
        for (Map.Entry<Integer, List<String>> entry : productData.entrySet()) {
            //проверяем, чтобы было 4 значения в строке для таблицы продуктов
            assertEquals(4, entry.getValue().size());
        }
    }

    @Test
    void readDiscountCardTableTest() {
        Map<Integer, List<String>> discountCardData = daoWorker.read("discount_card");
        assertFalse(discountCardData.isEmpty());
        for (Map.Entry<Integer, List<String>> entry : discountCardData.entrySet()) {
            //проверяем, чтобы было 2 значения в строке для таблицы дисконтных карточек
            assertEquals(2, entry.getValue().size());
        }
    }


    @Test
    void writeReceiptTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 4, "Product 1", 10.0, 10, true, 0.78, 100));
        products.add(new Product(1, 4, "Product 2", 10.0, 10, true, 0.78, 100));
        Receipt receipt = new Receipt(products, 1234, 10.0, 3, 100, 0.89, 0.3, "08.07.2024", "18:00:00");
        daoWorker.write(receipt);
        File resultFile = new File(daoWorker.getResultRootPath());
        assertTrue(resultFile.exists());

    }

    @Test
    void deleteFilesTest() {
        daoWorker.deleteFiles();
        File resultFile = new File(daoWorker.getResultRootPath());
        assertFalse(resultFile.exists());
    }
}

