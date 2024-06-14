package Controller;

import Model.Data;

import java.util.regex.Pattern;

public class Controller {
    private Data data;

    public Controller(Data data) {
        this.data = data;
    }

    public boolean correctAmountOfData(){
        return data.correctAmountOfData();
    }
    public boolean checkFullName(){
        return data.checkFullName();
    }
    public boolean checkBirthday(){
        return data.checkBirthday();
    }
    public boolean checkNumberPhone(){
        return data.checkNumberPhone();
    }
    public boolean checkGender(){
        return data.checkGender();
    }
}
