package main.java.ru.clevertec.check.strategy;

import main.java.ru.clevertec.check.exception.IncorrectInputException;
import main.java.ru.clevertec.check.worker.csv.singleton.CSVFileWorker;
import main.java.ru.clevertec.check.entities.Product;

import java.util.*;

public class DataTransferIdQuantity implements DataTransferStrategy{
    private String str = "-";
    @Override
    public List<Product> getResult(String[] args) {
        Map<Integer, Integer> productQuantities = new HashMap<>();
        //кладём в мапу данные из аргументов (id продукта - количество)
        try{
            for (String arg : args) {
                if (arg.contains(str) && !arg.startsWith("balanceDebitCard=")) {
                    Integer id = Integer.valueOf(arg.split(str)[0]);
                    String quantity = arg.split("-")[1];

                    if(isValidValues(id, quantity)){
                        if (productQuantities.containsKey(id)) {
                            productQuantities.put(id, productQuantities.get(id) + Integer.parseInt(quantity));
                        } else {
                            productQuantities.put(id, Integer.parseInt(quantity));
                        }
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }
            }
        }
        catch (IncorrectInputException | NumberFormatException e){
            try{
                throw new IncorrectInputException("BAD REQUEST");
            }
            catch (IncorrectInputException m){
                m.printStackTrace();
                m.makeCsvError();
            }
        }

        List<Product> products = new ArrayList<>();

        //мапа из файла
        Map<Integer, List<String>> productsFile;
        CSVFileWorker csvFileWorker = CSVFileWorker.getINSTANCE();
        productsFile = csvFileWorker.read(csvFileWorker.getCsvProductsPath());

        try{
            if(productsFile.isEmpty())
                throw new IncorrectInputException("BAD REQUEST");
        }
        catch (IncorrectInputException e){
            e.printStackTrace();
            e.makeCsvError();
        }

        //переводим данные с мапы в объекты-продукты в соответствии с данными из файла
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            int id = entry.getKey();
            int quantity = entry.getValue();
            try {
                for (Map.Entry<Integer, List<String>> p : productsFile.entrySet()) {
                    if (id >= Collections.min(productsFile.keySet()) &&
                            id <= Collections.max(productsFile.keySet())){
                        if (p.getKey().equals(id)) {
                            Product product = new Product();
                            product.setId(id);
                            product.setQuantity(quantity);
                            product.setDescription(p.getValue().get(0));
                            product.setPrice(Double.parseDouble(p.getValue().get(1)));
                            product.setQuantity_in_stock(Integer.parseInt(p.getValue().get(2)));
                            product.setWholesale_product(Boolean.parseBoolean(p.getValue().get(3)));
                            product.setTotalPrice((double) Math.round(product.getPrice() * product.getQuantity() * 100) / 100);
                            if (product.getQuantity_in_stock() - product.getQuantity() >= 0) {
                                products.add(product);
                            } else
                                throw new IncorrectInputException("BAD REQUEST");
                        }
                    } else
                        throw new IncorrectInputException("BAD REQUEST");
                }
            }
            catch (IncorrectInputException e){
                e.printStackTrace();
                e.makeCsvError();
            }
        }

        return products;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    private static boolean isValidValues(Integer a, String b) {
        return String.valueOf(a).matches("^\\d+$") && b.matches("^\\d+$");
    }
}
