package strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.exception.IncorrectInputException;
import ru.clevertec.check.strategy.DataTransferArgs;

import java.io.IOException;
import java.util.List;

class DataTransferArgsTest {

    @Test
    void testGetResultWithValidInput() {
        String[] args = {
                "saveToFile=./output.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/mydatabase",
                "datasource.username=myusername",
                "datasource.password=mypassword"
        };
        DAOWorker daoWorker = DAOWorker.getINSTANCE();
        DataTransferArgs dataTransferArgs = new DataTransferArgs();
        List<String> result = dataTransferArgs.getResult(args);

        Assertions.assertTrue(result.isEmpty());
        Assertions.assertEquals("./output.csv", daoWorker.getResultRootPath());
        Assertions.assertEquals("jdbc:postgresql://localhost:5432/mydatabase", DAOWorker.getURL());
        Assertions.assertEquals("myusername", DAOWorker.getUSERNAME());
        Assertions.assertEquals("mypassword", DAOWorker.getPASSWORD());
    }

}