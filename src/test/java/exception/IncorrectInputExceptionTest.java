package exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.exception.IncorrectInputException;

import static org.mockito.Mockito.verify;

class IncorrectInputExceptionTest {

    @BeforeEach
    void setUp() {
        DAOWorker daoWorker = DAOWorker.getINSTANCE();
        daoWorker.setResultRootPath("./result.csv");
        DAOWorker.setURL("jdbc:postgresql://localhost:5432/check");
        DAOWorker.setUSERNAME("postgres");
        DAOWorker.setPASSWORD("pepino111");
    }

    @Test
    void testConstructor() {
        String expectedMessage = "BAD REQUEST";
        IncorrectInputException exception = new IncorrectInputException(expectedMessage);
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}