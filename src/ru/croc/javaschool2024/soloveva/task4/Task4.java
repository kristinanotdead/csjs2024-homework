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
        Scanner sc = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if(input.equals("exit")){
                break;
            }

            builder.append(input).append('\n');
        }

        String result = removeJavaComments(builder.toString());
        System.out.println(result);
    }
    public static String removeJavaComments(String input) {
        StringBuilder builder = new StringBuilder();
        boolean inBlockComment = false;
        boolean inString = false;

        for (int i = 0; i < input.length(); i++) {
            if (inBlockComment) {
                if (i < input.length() - 1 && input.charAt(i) == '*' && input.charAt(i + 1) == '/') {
                    inBlockComment = false;
                    i++;
                }
            } else if (inString){
                if (input.charAt(i) == '"') {
                    inString = false;
                }

                builder.append(input.charAt(i));
            } else {
                if (input.charAt(i) == '"'){
                    inString = true;
                    builder.append(input.charAt(i));
                    continue;
                }

                if (i < input.length() - 1 && input.charAt(i) == '/' && input.charAt(i + 1) == '*') {
                    inBlockComment = true;
                    i++;
                    continue;
                }

                if (i < input.length() - 1 && input.charAt(i) == '/' && input.charAt(i + 1) == '/') {
                    while(i < input.length() - 1 && input.charAt(i) != '\n'){
                        i++;
                    }

                    builder.append('\n');
                    continue;
                }

                builder.append(input.charAt(i));
            }
        }

        return builder.toString();
    }
}