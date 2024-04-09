package ru.croc.javaschool2024.soloveva.task11.readers;

import ru.croc.javaschool2024.soloveva.task11.errors.IncorrectMovieException;
import ru.croc.javaschool2024.soloveva.task11.models.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieReader {
    public static List<Movie> readMoviesFromFile(String fileName) throws IOException, IncorrectMovieException {
        List<Movie> movies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        movies.add(new Movie(id, title));
                    } catch (NumberFormatException e) {
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
