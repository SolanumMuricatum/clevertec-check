package strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.exception.IncorrectInputException;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.strategy.DataTransferIdQuantity;

import java.util.ArrayList;
import java.util.List;

class DataTransferIdQuantityTest {

    @BeforeEach
    void setUp() {
        DAOWorker daoWorker = DAOWorker.getINSTANCE();
        daoWorker.setResultRootPath("./result.csv");
        DAOWorker.setURL("jdbc:postgresql://localhost:5432/check");
        DAOWorker.setUSERNAME("postgres");
        DAOWorker.setPASSWORD("pepino111");
    }

    @Test
    void testGetResultWithValidInput() {
        DataTransferIdQuantity strategy = new DataTransferIdQuantity();
        String[] args = {"1-5", "2-3", "1-2"};
        List<Product> products;
        products = strategy.getResult(args);

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals(1, products.get(0).getId());
        Assertions.assertEquals(7, products.get(0).getQuantity());
        Assertions.assertEquals(2, products.get(1).getId());
        Assertions.assertEquals(3, products.get(1).getQuantity());
    }

}