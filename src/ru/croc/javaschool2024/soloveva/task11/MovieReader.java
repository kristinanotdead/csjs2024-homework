package ru.croc.javaschool2024.soloveva.task11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieReader {
    public static Map<Integer, Movie> readMoviesFromFile(String fileName) throws IOException, IncorrectMovieException {
        Map<Integer, Movie> movies = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        movies.put(id, new Movie(id, title));
                    } catch (NumberFormatException e){
                        throw new IncorrectMovieException(line);
                    }
                } else {
                    throw new IncorrectMovieException(line);
                }
            }
        }

        return movies;
    }
}
