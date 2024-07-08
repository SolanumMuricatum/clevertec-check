package ru.clevertec.check.decorator;

import ru.clevertec.check.entities.BasicReceipt;
import ru.clevertec.check.entities.Product;

import java.util.List;

public class WholeSaleDecorator extends ReceiptDecorator{
    public WholeSaleDecorator(BasicReceipt basicReceipt) {
        super(basicReceipt);
    }

    @Override
    public List<Product> getProducts() {
        //устанавливаем скидку для wholesale products
        List<Product> products = super.getProducts();
        for (Product product : products) {
            if(product.isWholesale_product() && product.getQuantity()>=5){
                product.setDiscount((double) Math.round(product.getPrice() * product.getQuantity() * 10 / 100 * 100) /100);
            }
        }
        return products;
    }

}
