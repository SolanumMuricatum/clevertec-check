package strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.exception.IncorrectInputException;
import ru.clevertec.check.strategy.DataTransferBalanceDebitCard;

import java.util.List;

class DataTransferBalanceDebitCardTest {

    @Test
    void testGetResultWithValidBalance() {
        String[] args = {"balanceDebitCard=100.50"};
        DataTransferBalanceDebitCard strategy = new DataTransferBalanceDebitCard();
        List<Double> result = strategy.getResult(args);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(100.50, result.get(0));
    }

    @Test
    void testIsValidBalance() {
        Assertions.assertTrue(DataTransferBalanceDebitCard.isValidBalance("100.50"));
        Assertions.assertTrue(DataTransferBalanceDebitCard.isValidBalance("0"));
        Assertions.assertTrue(DataTransferBalanceDebitCard.isValidBalance("-50.99"));
        Assertions.assertTrue(DataTransferBalanceDebitCard.isValidBalance("1234"));
        Assertions.assertFalse(DataTransferBalanceDebitCard.isValidBalance("abc"));
        Assertions.assertFalse(DataTransferBalanceDebitCard.isValidBalance("100.50.20"));
    }
}