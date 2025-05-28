package main.java.ru.clevertec.check.worker.csv.singleton;

import main.java.ru.clevertec.check.entities.BasicReceipt;
import main.java.ru.clevertec.check.entities.Product;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVFileWorker {
    private static CSVFileWorker INSTANCE = new CSVFileWorker();
    private String csvProductsPath;
    private String csvDiscountCardsPath = "./src/main/resources/discountCards.csv";
    private String resultRootPath;
    private String errorRootPath;
    private char separator = ';';
    private String currency = "$";

    //устанавливаем пути для сохранения по умолчанию при инициализации объекта класса
    {
        File file = new File("");
        try {
            String rootPath = file.getCanonicalPath();
            resultRootPath = rootPath + "\\result.csv";
            errorRootPath = rootPath + "\\error_result.csv";
        } catch (IOException e) {
            errorWriter("INTERNAL SERVER ERROR");
        }
    }

    private CSVFileWorker(){};
    public static CSVFileWorker getINSTANCE(){
        return INSTANCE;
    }

    public Map<Integer, List<String>> read(String path) {
        Map<Integer, List<String>> csvData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                if (lineNumber > 0) { // Пропускаем первую строку (заголовки)
                    String[] fields = line.split(String.valueOf(separator));
                    int id = Integer.parseInt(fields[0]);
                    List<String> rowData = new ArrayList<>(Arrays.asList(fields).subList(1, fields.length));
                    csvData.put(id, rowData);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            errorWriter("INTERNAL SERVER ERROR");
        }

        return csvData;
    }

    public void write(BasicReceipt basicReceipt) {
        try (FileWriter writer = new FileWriter(resultRootPath)) {
            Date dateNow = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");

            writer.write("Date" + separator + "Time\n");
            basicReceipt.setDate(dateFormatter.format(dateNow));
            basicReceipt.setTime(timeFormatter.format(dateNow));
            writer.write(basicReceipt.getDate() + separator + basicReceipt.getTime() + "\n\n");

            writer.write("QTY" + separator + "DESCRIPTION" + separator + "PRICE" + separator + "DISCOUNT" + separator + "TOTAL\n");
            for (Product product : basicReceipt.getProducts()) {
                writer.write(String.valueOf(product.getQuantity()) + separator + product.getDescription() + separator + product.getPrice() + currency + separator + product.getDiscount() + currency + separator + product.getTotalPrice() + currency + "\n");
            }
            writer.write("\n");

            if (basicReceipt.getDiscountCard() != 0) {
                writer.write("DISCOUNT CARD" + separator + "DISCOUNT PERCENTAGE\n");
                writer.write(String.valueOf(basicReceipt.getDiscountCard()) + separator + basicReceipt.getDiscountPercentage() + "%\n\n");
            }

            writer.write("TOTAL PRICE" + separator + "TOTAL DISCOUNT" + separator + "TOTAL WITH DISCOUNT\n");
            writer.write(basicReceipt.getTotalPrice() + currency + separator + basicReceipt.getTotalDiscount() + currency + separator + basicReceipt.getTotalPriceWithDiscount() + currency);

            basicReceipt.printReceipt(basicReceipt, separator, currency);
        } catch (IOException e) {
            errorWriter("INTERNAL SERVER ERROR");
        }
    }

    public void errorWriter(String str) {
        try (FileWriter writer = new FileWriter(resultRootPath)) { // true for append mode
            writer.write("ERROR\n" + str + "\n");
        } catch (IOException e) {
            // Log the error or handle it in a different way, without recursive calls
            errorWriter("INTERNAL SERVER ERROR");
        }
    }

    public void deleteFiles() {
        File file1 = new File(resultRootPath);
        File file2 = new File(errorRootPath);
        if (file1.exists()) {
            file1.delete();
        }
        if (file2.exists()) {
            file2.delete();
        }
    }

    public String getCsvProductsPath() {
        return csvProductsPath;
    }

    public void setCsvProductsPath(String csvProductsPath) {
        this.csvProductsPath = csvProductsPath;
    }

    public String getCsvDiscountCardsPath() {
        return csvDiscountCardsPath;
    }

    public void setCsvDiscountCardsPath(String csvDiscountCardsPath) {
        this.csvDiscountCardsPath = csvDiscountCardsPath;
    }

    public String getRootPath() {
        return resultRootPath;
    }

    public void setRootPath(String rootPath) {
        resultRootPath = rootPath;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public String getResultRootPath() {
        return resultRootPath;
    }

    public void setResultRootPath(String resultRootPath) {
        this.resultRootPath = resultRootPath;
    }

    public String getErrorRootPath() {
        return errorRootPath;
    }

    public void setErrorRootPath(String errorRootPath) {
        this.errorRootPath = errorRootPath;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}