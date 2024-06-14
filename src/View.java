import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {
    public void run(){
        String input = prompt("Введите данные, разделенные пробелом " +
                "(В формате: Фамилия Имя Отчество дата_рождения номер_телефона пол):\n");
        String[] data = input.split(" ");
        if(!correctAmountOfData(data)) throw new RuntimeException("incorrect amount of data");
        if(!checkFullName(data[0]) | !checkFullName(data[1]) | !checkFullName(data[2]))
            throw new RuntimeException("Name entered incorrectly");
        checkBirthday(data[3]);
        if(!checkNumberPhone(data[4])) throw new RuntimeException("incorrect number phone");
        if(!checkGender(data[5])) throw new RuntimeException("incorrect gender");
        createFile(data);
        System.out.println("Данные сохранены в папке PhoneBook");
    }
    public String prompt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }
    public boolean correctAmountOfData(String[] data){
        return data.length == 6;
    }
    public boolean checkFullName(String name){
        return Pattern.matches("[da-zA-Zа-яёА-ЯЁ-]+", name);
    }
    public void checkBirthday(String dateBirthday){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            date.parse(dateBirthday);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("incorrect date");
        }
    }
    public boolean checkNumberPhone(String number){
        return Pattern.matches("[0-9+-]+", number);
    }
    public boolean checkGender(String gender){
        if(gender.length() == 1){
            return Pattern.matches("[fm]+", gender);
        }
        else return false;
    }
    public static void createFile(String[] data) {
        try (FileWriter fileWriter = new FileWriter(String.format("PhoneBook/%s.txt", data[0]), true)){
            for (String str : data) {
                fileWriter.write("<" + str + ">");
            }
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
