package ru.croc.javaschool2024.soloveva.task8;

import java.util.ArrayList;
import java.util.concurrent.*;

import static ru.croc.javaschool2024.soloveva.task8.MD5Hashing.hashPassword;

public class Task8 {
    private static final int PASSWORD_LENGTH = 7;
    private static final char MIN_CHAR = 'a';
    private static final char MAX_CHAR = 'z';

    public static void main(String[] args) {
        try {
            String password = decodePassword(Integer.parseInt(args[0]), args[1]);

            if(password == null){
                System.out.println("Пароль не найден");
            } else {
                System.out.println(password);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Неправильный формат ввода");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Неверное количество аргументов");
        }
    }

    public static String decodePassword(Integer threadNums, String hash) {
        ExecutorService pool = Executors.newFixedThreadPool(threadNums);
        CompletionService<String> completionService = new ExecutorCompletionService<>(pool);
        ArrayList<Callable<String>> tasks = new ArrayList<>();

        StringBuilder startPassword = new StringBuilder();

        for (int j = 0; j < PASSWORD_LENGTH; j++) {
            startPassword.append(MIN_CHAR);
        }

        for (int i = 0; i < 26; i++) {
            startPassword.setCharAt(0, (char) ('a' + i));
            StringBuilder passwordForThread = new StringBuilder(startPassword);

            tasks.add(() -> {
                while (true) {
                    if (hashPassword(passwordForThread.toString()).equals(hash)) {
                        return passwordForThread.toString();
                    }

                    int pos = PASSWORD_LENGTH - 1;
                    while (pos > 0 && passwordForThread.charAt(pos) == MAX_CHAR) {
                        pos--;
                    }

                    if (pos == 0) {
                        return null;
                    }

                    passwordForThread.setCharAt(pos, (char) (passwordForThread.charAt(pos) + 1));

                    for (int j = pos + 1; pos < PASSWORD_LENGTH - 1; pos++) {
                        passwordForThread.setCharAt(j, MIN_CHAR);
                    }
                }
            });
        }

        for (Callable<String> task : tasks) {
            completionService.submit(task);
        }

        int received = 0;
        boolean hasErrors = false;

        while (received < 26 && !hasErrors) {
            try {
                Future<String> resultFuture = completionService.take();
                String result = resultFuture.get();
                received++;

                if (result != null) {
                    pool.shutdownNow();
                    return result;
                }
            } catch (Exception e) {
                hasErrors = true;
                System.out.println(e);
            }
        }

        return null;
    }
}
