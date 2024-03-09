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
    public static void printBytes(double in) {
        int countDiv = 0;
        while (in >= 1024) {
            in /= 1024;
            countDiv++;
        }
        String suffix = getSuffix(countDiv);
        System.out.printf("%.1f %s", in, suffix);
    }
    public static void main(String[] args) {
        try{
            printBytes(Long.parseLong(args[0]));
        } catch (NumberFormatException ex){
            System.out.println("Неправильный формат ввода");
        }
    }
}