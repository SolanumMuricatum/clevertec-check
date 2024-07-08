package main.java.ru.clevertec.check.decorator;

import main.java.ru.clevertec.check.worker.csv.singleton.CSVFileWorker;
import main.java.ru.clevertec.check.entities.BasicReceipt;
import main.java.ru.clevertec.check.entities.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCardDecorator extends ReceiptDecorator{
    public DiscountCardDecorator(BasicReceipt basicReceipt) {
        super(basicReceipt);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = super.getProducts();

        //получили данные из файла с дисконтными карточками
        Map<Integer, List<String>> cards = new HashMap<>();
        CSVFileWorker csvFileWorker = CSVFileWorker.getINSTANCE();
        cards = csvFileWorker.read(csvFileWorker.getCsvDiscountCardsPath());

        int discount = 0;

        if(basicReceipt.getDiscountCard() == 0){
            return products;
        }

        boolean foundDiscountCard = false; //для проверки наличия карточки

        for (Map.Entry<Integer, List<String>> p : cards.entrySet()) {
            if (basicReceipt.getDiscountCard() == Integer.parseInt(p.getValue().get(0))) {
                discount = Integer.parseInt(p.getValue().get(1));
                foundDiscountCard = true;
                break;
            }
        }

        if (!foundDiscountCard) {
            discount = 2;
        }

        //устанавливаем в чеке скидку по карточке
        basicReceipt.setDiscountPercentage(discount);

        //расчитываем скидку по дисконтной карте для продуктов, учитывая wholesale products
        for (Product product : products) {
            if(!(product.isWholesale_product() && product.getQuantity()>=5)){
                product.setDiscount((double) Math.round(product.getPrice() * product.getQuantity() * discount / 100 * 100) /100);
            }
        }
        return products;
    }

}
