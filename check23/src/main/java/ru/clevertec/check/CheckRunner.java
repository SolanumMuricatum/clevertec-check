package ru.clevertec.check;

import ru.clevertec.check.decorator.DiscountCardDecorator;
import ru.clevertec.check.decorator.ReceiptDecorator;
import ru.clevertec.check.decorator.TotalCalculationsDecorator;
import ru.clevertec.check.decorator.WholeSaleDecorator;
import ru.clevertec.check.entities.Receipt;
import ru.clevertec.check.strategy.*;
import ru.clevertec.check.dao.DAOWorker;

public class CheckRunner {

    public static void main(String[] args) {

        //String[] str = {"3-1", "2-5","5-1", "discountCard=4444", "balanceDebitCard=100", "saveToFile=./result.csv", "datasource.url=jdbc:postgresql://localhost:5432/check", "datasource.username=postgres", "datasource.password=pepino111"};
        // тестовый ввод не через консоль

        DAOWorker daoWorker = DAOWorker.getINSTANCE();
        daoWorker.deleteFiles(); //удаляем файлы ./result.csv, если они есть

        Transfer transfer = new Transfer(new DataTransferArgs(), args);
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

        daoWorker.write(receipt);  //создаём csv-чек

    }

}
