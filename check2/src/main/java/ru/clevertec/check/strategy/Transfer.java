package main.java.ru.clevertec.check.strategy;

import main.java.ru.clevertec.check.entities.Product;

import java.util.List;

public class Transfer {
    private DataTransferStrategy dataTransferStrategy;
    private String[] args;

    public Transfer(DataTransferStrategy dataTransferStrategy, String[] args){
        this.dataTransferStrategy = dataTransferStrategy;
        this.args = args;
    }

    public void setPayStrategy(DataTransferStrategy dataTransferStrategy){
        this.dataTransferStrategy = dataTransferStrategy;
    }

    public <T>List<T> getResult(){
        return dataTransferStrategy.getResult(args);
    }

}
