package main.java.ru.clevertec.check.strategy;

import main.java.ru.clevertec.check.exception.IncorrectInputException;

import java.util.ArrayList;
import java.util.List;

public class DataTransferDiscountCard implements DataTransferStrategy{
    private String str = "discountCard=";
    @Override
    public List<Integer> getResult(String[] args) {
        List<Integer> list = new ArrayList<>();
        int discountCard = 0;
        try {
            for (String arg : args) {
                if (arg.startsWith(str)) {
                    discountCard = Integer.parseInt(arg.split("=")[1]);
                }
            }
            if (discountCard == 0 || isValidDiscountCard(discountCard)){
                list.add(discountCard);
            }
            else
                throw new IncorrectInputException("BAD REQUEST");

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

        return list;
    }

    private static boolean isValidDiscountCard(int balanceValue) {
        //регулярное выражение проверяет наличие четырёх цифр
        return String.valueOf(balanceValue).matches("\\d{4}");
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
