package ru.croc.javaschool2024.soloveva.task7;

import java.util.Scanner;
import java.util.Stack;

public class Task7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String output = normalizePath(input);
        System.out.println(output);
    }

    public static String normalizePath(String path) {
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();
        int parentsCount = 0;

        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    parentsCount++;
                }
            } else if (!part.equals(".") && !part.isEmpty()) {
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
            result.insert(0, "/");
        }

        for(int i = 0; i < parentsCount - 1; i++){
            result.insert(0, "/..");
        }

        if(parentsCount > 0){
            result.insert(0, "..");
        }

        return result.toString();
    }

}


