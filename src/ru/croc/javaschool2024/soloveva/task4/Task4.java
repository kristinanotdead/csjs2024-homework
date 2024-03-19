package ru.croc.javaschool2024.soloveva.task4;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
public class Task4 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            String result = removeJavaComments(input);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeJavaComments(String input) {
        Pattern pattern = Pattern.compile("(//.*?$|/\\*.*?\\*/)", Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }
}