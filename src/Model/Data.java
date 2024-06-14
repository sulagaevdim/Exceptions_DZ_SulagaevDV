package Model;

import View.DataException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Data {
    private String[] data;

    public Data(String[] data) {
        this.data = data;
    }
    public boolean correctAmountOfData(){
        return data.length == 6;
    }
    public boolean checkFullName(){
        if (Pattern.matches("[da-zA-Zа-яёА-ЯЁ-]+", data[0]) &
                Pattern.matches("[da-zA-Zа-яёА-ЯЁ-]+", data[1]) &
                Pattern.matches("[da-zA-Zа-яёА-ЯЁ-]+", data[2]))
            return true;
        else return false;
    }
    public boolean checkBirthday(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            date.parse(data[3]);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public boolean checkNumberPhone(){
        return Pattern.matches("[0-9+-]+", data[4]);
    }
    public boolean checkGender(){
        if(data[5].length() == 1){
            return Pattern.matches("[fm]+", data[5]);
        }
        else return false;
    }
}
