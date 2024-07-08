package strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.strategy.DataTransferDiscountCard;

import java.util.List;

class DataTransferDiscountCardTest {

    @Test
    void testGetResultWithValidDiscountCard() {
        String[] args = {"discountCard=1234"};
        DataTransferDiscountCard strategy = new DataTransferDiscountCard();
        List<Integer> result = strategy.getResult(args);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1234, result.get(0));
    }

    @Test
    void testGetResultWithMissingDiscountCard() {
        String[] args = {"someOtherParameter=123"};
        DataTransferDiscountCard strategy = new DataTransferDiscountCard();

        List<Integer> result = strategy.getResult(args);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(0, result.get(0));
    }

    @Test
    void testIsValidDiscountCard() {
        Assertions.assertTrue(DataTransferDiscountCard.isValidDiscountCard(1234));
        Assertions.assertFalse(DataTransferDiscountCard.isValidDiscountCard(12345));
        Assertions.assertFalse(DataTransferDiscountCard.isValidDiscountCard(123));
    }
}