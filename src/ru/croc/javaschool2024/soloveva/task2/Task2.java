package ru.croc.javaschool2024.soloveva.task2;

public class Task2 {
    public static String getSuffix(int countDiv) {
        switch (countDiv) {
            case 0:
                return "B";
            case 1:
                return "KB";
            case 2:
                return "MB";
            case 3:
                return "GB";
            case 4:
                return "TB";
        }
        return "Too big";
    }
    public static void printBytes(long in) {
        int countDiv = 0;
        double val = in;
        while (val >= 1024) {
            val /= 1024;
            countDiv++;
        }
        String suffix = getSuffix(countDiv);
        System.out.printf("%.1f %s", val, suffix);
    }
    public static void main(String[] args) {
        try{
            printBytes(Long.parseLong(args[0]));
        } catch (NumberFormatException ex){
            System.out.println("Неправильный формат ввода");
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Нет входных данных");
        }
    }
}