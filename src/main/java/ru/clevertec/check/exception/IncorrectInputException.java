package ru.clevertec.check.exception;

import ru.clevertec.check.dao.DAOWorker;

public class IncorrectInputException extends Exception{ //класс для выброса собственного исключения
    String message;
    public IncorrectInputException(String message) {
        this.message = message;
    }
    public void makeCsvError(){
        DAOWorker.errorWriter(this.message);
        System.exit(1);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
