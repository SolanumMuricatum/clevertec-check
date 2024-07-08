package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.decorator.DiscountCardDecorator;
import main.java.ru.clevertec.check.decorator.ReceiptDecorator;
import main.java.ru.clevertec.check.decorator.TotalCalculationsDecorator;
import main.java.ru.clevertec.check.decorator.WholeSaleDecorator;
import main.java.ru.clevertec.check.entities.Receipt;
import main.java.ru.clevertec.check.strategy.*;
import main.java.ru.clevertec.check.worker.csv.singleton.CSVFileWorker;

public class CheckRunner {

    public static void main(String[] args) {

        //String[] str = {"3-1", "2-5", "5-10", "discountCard=1111", "balanceDebitCard=100", "pathToFile=./src/main/resources/products.csv", "saveToFile=./result.csv"};
        // тестовый ввод не через консоль

        CSVFileWorker csvFileWorker = CSVFileWorker.getINSTANCE();
        csvFileWorker.deleteFiles(); //удаляем файлы result.csv/error_result.csv, если они есть

        Transfer transfer = new Transfer(new DataTransferPathToFile(), args);
        transfer.getResult(); //указываем нужные пути к файлам

        transfer.setPayStrategy(new DataTransferIdQuantity());
        //создаём чек
        Receipt receipt = new Receipt();
        //с помощью стратегии создаём список продуктов
        receipt.setProducts(transfer.getResult());

        //меняем стратегию. сохраняем дисконтную карту
        transfer.setPayStrategy(new DataTransferDiscountCard());
        receipt.setDiscountCard((Integer) transfer.getResult().get(0));

        //меняем стратегию. сохраняем сумму с дебитовой карты
        transfer.setPayStrategy(new DataTransferBalanceDebitCard());
        receipt.setBalanceDebitCard((Double) transfer.getResult().get(0));

        //используем декоратор для получения скидок
        ReceiptDecorator receiptDecorator = new TotalCalculationsDecorator(
                new DiscountCardDecorator(
                        new WholeSaleDecorator(receipt)));

        receipt.setProducts(receiptDecorator.getProducts()); //создаём чек

        csvFileWorker.write(receipt);  //создаём csv-чек

    }

}
