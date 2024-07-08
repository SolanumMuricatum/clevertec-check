package ru.clevertec.check.decorator;

import ru.clevertec.check.entities.BasicReceipt;
import ru.clevertec.check.entities.Product;
import ru.clevertec.check.exception.IncorrectInputException;

import java.util.List;

public class TotalCalculationsDecorator extends ReceiptDecorator{
    //декоратор для вычисления итоговой суммы, скидки и суммы со скидкой
    public TotalCalculationsDecorator(BasicReceipt basicReceipt) {
        super(basicReceipt);
    }

    @Override
    public List<Product> getProducts(){

        List<Product> products = super.getProducts();
        double totalPrice = 0;
        double totalDiscount = 0;
        double totalPriceWithDiscount = 0;
        for(Product product: products){
            totalPrice+=product.getTotalPrice();
            totalDiscount+= product.getDiscount();
        }
        totalPriceWithDiscount = totalPrice - totalDiscount;
        basicReceipt.setTotalPrice((double) Math.round(totalPrice * 100) /100);
        basicReceipt.setTotalDiscount((double) Math.round(totalDiscount * 100) /100);
        basicReceipt.setTotalPriceWithDiscount((double) Math.round(totalPriceWithDiscount* 100) /100);
        try {
            if (basicReceipt.getBalanceDebitCard() - basicReceipt.getTotalPriceWithDiscount() < 0) {
                throw new IncorrectInputException("NOT ENOUGH MONEY");
            }
        }
        catch (IncorrectInputException e){
            e.printStackTrace();
            e.makeCsvError();
        }

        return products;
    }
}
