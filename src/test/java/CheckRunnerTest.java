import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.CheckRunner;
import ru.clevertec.check.dao.DAOWorker;
import ru.clevertec.check.entities.Receipt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CheckRunnerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        DAOWorker.getINSTANCE().deleteFiles();
    }

    @Test
    void mainTest() {
        String[] args = {"3-1", "discountCard=4444", "balanceDebitCard=100", "saveToFile=./result.csv", "datasource.url=jdbc:postgresql://localhost:5432/check", "datasource.username=postgres", "datasource.password=pepino111"};
        CheckRunner.main(args);

        File resultFile = new File("./result.csv");
        assertTrue(resultFile.exists());

        try {
            String fileContent = new String(Files.readAllBytes(Paths.get("./result.csv")));
            assertTrue(fileContent.contains("4444"));
            assertTrue(fileContent.contains("Yogurt 400g"));
        } catch (Exception e) {
            fail("Error reading result file:( " + e.getMessage());
        }
    }
}