package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.decorator.DiscountCardDecorator;
import main.java.ru.clevertec.check.decorator.ReceiptDecorator;
import main.java.ru.clevertec.check.decorator.TotalCalculationsDecorator;
import main.java.ru.clevertec.check.decorator.WholeSaleDecorator;
import main.java.ru.clevertec.check.entities.Receipt;
import main.java.ru.clevertec.check.strategy.DataTransferBalanceDebitCard;
import main.java.ru.clevertec.check.strategy.DataTransferDiscountCard;
import main.java.ru.clevertec.check.strategy.Transfer;
import main.java.ru.clevertec.check.strategy.DataTransferIdQuantity;
import main.java.ru.clevertec.check.worker.csv.singleton.CSVFileWorker;

public class CheckRunner {

    public static void main(String[] args) {

        //String[] str = {"3-1", "2-5", "5-10", "discountCard=1111", "balanceDebitCard=100"};
        // тестовый ввод не через консоль

        CSVFileWorker csvFileWorker = CSVFileWorker.getINSTANCE();
        csvFileWorker.deleteFiles();  //сперва удаляем файлы-результаты после прошлого запуска

        Transfer transfer = new Transfer(new DataTransferIdQuantity(), args);
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
