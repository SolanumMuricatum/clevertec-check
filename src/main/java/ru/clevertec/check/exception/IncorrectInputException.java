package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.worker.csv.singleton.CSVFileWorker;

public class IncorrectInputException extends Exception{ //класс для выброса собственного исключения
    String message;
    public IncorrectInputException(String message) {
        this.message = message;
    }
    public void makeCsvError(){
        CSVFileWorker csvFileWorker = CSVFileWorker.getINSTANCE();
        csvFileWorker.errorWriter(this.message);
        System.exit(1);
    }
}
