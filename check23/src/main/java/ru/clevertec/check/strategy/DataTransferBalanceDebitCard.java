package ru.clevertec.check.strategy;

import ru.clevertec.check.exception.IncorrectInputException;

import java.util.ArrayList;
import java.util.List;

public class DataTransferBalanceDebitCard implements DataTransferStrategy{
    private String str = "balanceDebitCard="; //для проверки правильности написания выражения
    @Override
    public List<Double> getResult(String[] args) {
        List<Double> list = new ArrayList<>();
        String balanceDebitCard = null;
        boolean hasBalanceDebitCard = false;
        try {
            for (String arg : args) {
                if (arg.startsWith(str)) {
                    hasBalanceDebitCard = true;
                    balanceDebitCard = (arg.split("=")[1]);
                }
            }
            if (!hasBalanceDebitCard) {
                throw new IncorrectInputException("BAD REQUEST");
            }
            if (isValidBalance(balanceDebitCard)) {
                list.add(Double.parseDouble(balanceDebitCard));
            } else
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

    public static boolean isValidBalance(String balanceValue) {
        return balanceValue.matches("^(-?\\d+(\\.\\d+)?|\\d{2,4})$");
    }
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
