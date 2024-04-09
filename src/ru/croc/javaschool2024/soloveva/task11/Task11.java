package ru.croc.javaschool2024.soloveva.task11;

import ru.croc.javaschool2024.soloveva.task11.errors.IncorrectHistoryException;
import ru.croc.javaschool2024.soloveva.task11.errors.IncorrectMovieException;
import ru.croc.javaschool2024.soloveva.task11.models.User;
import ru.croc.javaschool2024.soloveva.task11.readers.UserHistoryReader;
import ru.croc.javaschool2024.soloveva.task11.services.MovieRecommendationAlgorithm;

import java.io.*;
import java.util.*;

public class Task11 {
    public static void main(String[] args) {
        String moviesFileName = "src/ru/croc/javaschool2024/soloveva/task11/resources/movies.txt";
        String historyFileName = "src/ru/croc/javaschool2024/soloveva/task11/resources/history.txt";

        MovieRecommendationAlgorithm recommendationSystem = null;

        try {
            recommendationSystem = new MovieRecommendationAlgorithm(moviesFileName, historyFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов с ресурсами!");
            return;
        } catch (IncorrectMovieException | IncorrectHistoryException e) {
            System.out.println(e);
            return;
        }

        Scanner sc = new Scanner(System.in);
        User currentUser = null;

        try {
            currentUser = UserHistoryReader.readUserHistory(0, sc.nextLine());
        } catch (IncorrectHistoryException e) {
            System.out.println(e);
            return;
        }

        String recommendedMovie = recommendationSystem.recommendMovie(currentUser);

        System.out.println(recommendedMovie);
    }
}

