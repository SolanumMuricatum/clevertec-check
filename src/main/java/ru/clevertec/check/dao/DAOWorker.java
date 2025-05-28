package ru.clevertec.check.dao;

import ru.clevertec.check.entities.BasicReceipt;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.exception.IncorrectInputException;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DAOWorker {
    private static final DAOWorker INSTANCE = new DAOWorker();
    private static String resultRootPath;
    private char separator = ';';
    private String currency = "$";

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection connection;


    //устанавливаем путь для сохранения по умолчанию при инициализации объекта класса
    //устанавливаем соединение с бд
    static {
        File file = new File("");
        try {
            String rootPath = file.getCanonicalPath();
            resultRootPath = rootPath + "\\result.csv";
        } catch (IOException e) {
            errorWriter("INTERNAL SERVER ERROR");
        }

        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            errorWriter("INTERNAL SERVER ERROR");
        }
    }
    private DAOWorker(){};

    public static DAOWorker getINSTANCE(){
        return INSTANCE;
    }

    public Map<Integer, List<String>> read(String table){
        Map<Integer, List<String>> csvData = new HashMap<>();
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            if(table.equals("product")) {
                Statement statement = connection.createStatement();
                String SQL = "SELECT * FROM product";
                ResultSet resultSet = statement.executeQuery(SQL);

                while (resultSet.next()) {
                    csvData.put(resultSet.getInt("id"),
                            Arrays.asList(resultSet.getString("description"),
                                    resultSet.getString("price"),
                                    resultSet.getString("quantity_in_stock"),
                                    resultSet.getString("wholesale_product")));
                }
            } else if (table.equals("discount_card")) {
                Statement statement = connection.createStatement();
                String SQL = "SELECT * FROM discount_card";
                ResultSet resultSet = statement.executeQuery(SQL);

                while (resultSet.next()) {
                    csvData.put(resultSet.getInt("id"),
                            Arrays.asList(resultSet.getString("number"),
                                    resultSet.getString("discount_amount")));
                }
            }
            else {
                throw new IncorrectInputException("BAD REQUEST");
            }

        } catch (SQLException | IncorrectInputException e ) {
          e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public static void errorWriter(String str) {
        try (FileWriter writer = new FileWriter(resultRootPath)) {
            writer.write("ERROR\n" + str + "\n");
        } catch (IOException e) {
            errorWriter("INTERNAL SERVER ERROR");
        }
    }

    public void deleteFiles() {
        File file1 = new File(resultRootPath);
        if (file1.exists()) {
            file1.delete();
        }
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
        DAOWorker.resultRootPath = resultRootPath;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        DAOWorker.URL = URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        DAOWorker.USERNAME = USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        DAOWorker.PASSWORD = PASSWORD;
    }
}