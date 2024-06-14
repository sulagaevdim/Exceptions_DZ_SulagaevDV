package View;

import Controller.Controller;
import Model.Data;

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
        Controller controller = new Controller(new Data(data));
        if(!controller.correctAmountOfData()) throw new DataException("Iincorrect amount of data");
        if(!controller.checkFullName()) throw new DataException("Name entered incorrectly");
        if(!controller.checkBirthday()) throw new DataException("Date entered incorrectly");
        if(!controller.checkNumberPhone()) throw new DataException("incorrect number phone");
        if(!controller.checkGender()) throw new DataException("incorrect gender");

        createFile(data);
        System.out.println("Данные сохранены в папке PhoneBook");
    }
    public String prompt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }
    public static void createFile(String[] data) {
        try (FileWriter fileWriter = new FileWriter(String.format("src/PhoneBook/%s.txt", data[0]), true)){
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
