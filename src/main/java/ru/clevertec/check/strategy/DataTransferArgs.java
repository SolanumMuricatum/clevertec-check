package ru.clevertec.check.strategy;

import ru.clevertec.check.exception.IncorrectInputException;
import ru.clevertec.check.dao.DAOWorker;

import java.util.ArrayList;
import java.util.List;

public class DataTransferArgs implements DataTransferStrategy{
    private String str1 = "saveToFile="; //для проверки правильности написания выражения
    private String datasource_url = "datasource.url=";
    private String datasource_username = "datasource.username=";
    private String datasource_password = "datasource.password=";
    @Override
    public List<String> getResult(String[] args) {
        List<String> list = new ArrayList<>();
        DAOWorker daoWorker = DAOWorker.getINSTANCE();
        boolean hasStr1 = false;
        boolean hasdatasourceurl = false;
        boolean hasdatasourceusername = false;
        boolean hasdatasourcepassword = false;
        String saveToFile;
        try {
            for (String arg : args) {
                if (arg.startsWith(str1)) {
                    hasStr1 = true;
                    if(arg.split("=").length == 2) {
                        saveToFile = (arg.split("=")[1]);
                        daoWorker.setResultRootPath(saveToFile);
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }
                if (arg.startsWith(datasource_url)){
                    hasdatasourceurl = true;
                    if(arg.split("=").length == 2) {
                        DAOWorker.setURL((arg.split("=")[1]));
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }
                if (arg.startsWith(datasource_username)){
                    hasdatasourceusername = true;
                    if(arg.split("=").length == 2) {
                        DAOWorker.setUSERNAME((arg.split("=")[1]));
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }
                if (arg.startsWith(datasource_password)){
                    hasdatasourcepassword = true;
                    if(arg.split("=").length == 2) {
                        DAOWorker.setPASSWORD((arg.split("=")[1]));
                    }
                    else
                        throw new IncorrectInputException("BAD REQUEST");
                }

            }
            if(!hasStr1 || !hasdatasourceurl || !hasdatasourceusername || !hasdatasourcepassword){
                throw new IncorrectInputException("BAD REQUEST");
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

    public String getDatasource_url() {
        return datasource_url;
    }

    public void setDatasource_url(String datasource_url) {
        this.datasource_url = datasource_url;
    }

    public String getDatasource_username() {
        return datasource_username;
    }

    public void setDatasource_username(String datasource_username) {
        this.datasource_username = datasource_username;
    }

    public String getDatasource_password() {
        return datasource_password;
    }

    public void setDatasource_password(String datasource_password) {
        this.datasource_password = datasource_password;
    }
}
