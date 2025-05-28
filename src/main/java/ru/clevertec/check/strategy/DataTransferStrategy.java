package ru.clevertec.check.strategy;

import java.util.List;

public interface DataTransferStrategy {
    //используется дженерик для возможности использовать разные типы стратегии
    public <T>List<T> getResult(String[] args);
}
