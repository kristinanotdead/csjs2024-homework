package ru.croc.javaschool2024.soloveva.task11;

// UserHistoryReader.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHistoryReader {
    public static Map<Integer, User> readUserHistoryFromFile(String fileName)
            throws IOException, IncorrectHistoryException {
        Map<Integer, User> users = new HashMap<>();
        int userId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userId++;
                User user = readUserHistory(userId, line);
                users.put(userId, user);
            }
        }

        return users;
    }

    public static User readUserHistory(int userId, String line) throws IncorrectHistoryException {
        User user = new User(userId);
        String[] parts = line.split(",");
        for (String part : parts) {
            try {
                int movieId = Integer.parseInt(part);
                user.addView(movieId);
            } catch (NumberFormatException e){
                throw new IncorrectHistoryException(line);
            }
        }

        return user;
    }
}
