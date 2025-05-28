package main.java.ru.clevertec.check.strategy;

import main.java.ru.clevertec.check.exception.IncorrectInputException;
import main.java.ru.clevertec.check.worker.csv.singleton.CSVFileWorker;

import java.util.ArrayList;
import java.util.List;

public class DataTransferPathToFile implements DataTransferStrategy{
    private String str1 = "pathToFile="; //для проверки правильности написания выражения
    private String str2 = "saveToFile=";
    @Override
    public List<String> getResult(String[] args) {
        List<String> list = new ArrayList<>();
        boolean hasStr1 = false;
        boolean hasStr2 = false;
        String pathToFile = null;
        String saveToFile = null;
        try {
            for (String arg : args) {
                if (arg.startsWith(str1)) {
                    hasStr1 = true;
                    if(arg.split("=").length == 2) {
                        pathToFile = (arg.split("=")[1]);
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }
                if (arg.startsWith(str2)){
                    hasStr2 = true;
                    if(arg.split("=").length == 2){
                        saveToFile = (arg.split("=")[1]);
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }
            }
            if (!hasStr1 && !hasStr2) {
                throw new IncorrectInputException("BAD REQUEST");
            }
            else if(!hasStr1){
                CSVFileWorker.getINSTANCE().setRootPath(saveToFile);
                throw new IncorrectInputException("BAD REQUEST");
            }
            else if(!hasStr2){
                throw new IncorrectInputException("BAD REQUEST");
            }
            else{
                CSVFileWorker.getINSTANCE().setRootPath(saveToFile);
                CSVFileWorker.getINSTANCE().setCsvProductsPath(pathToFile);
            }

        }
        catch (IncorrectInputException e){
            e.printStackTrace();
            e.makeCsvError();
        }

        return list;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}
