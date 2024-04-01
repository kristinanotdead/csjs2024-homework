package ru.croc.javaschool2024.soloveva.task11;

import java.io.IOException;
import java.util.*;

public class MovieRecommendationAlgorithm {
    private Map<Integer, Movie> movies;
    private Map<Integer, User> users;

    public MovieRecommendationAlgorithm(String moviesFileName, String historyFileName)
            throws IOException, IncorrectMovieException, IncorrectHistoryException {
        this.movies = MovieReader.readMoviesFromFile(moviesFileName);
        this.users = UserHistoryReader.readUserHistoryFromFile(historyFileName);
    }

    private Set<User> getUsersWithSameInterests(User currentUser){
        Set<User> usersSet = new HashSet<>();
        Set<Integer> currentUserMovies = currentUser.getViews().keySet();
        int currentUserMoviesCount = currentUserMovies.size();

        for(User user : users.values()){
            int sameMoviesCount = 0;

            for(Integer movieId : user.getViews().keySet()){
                if(currentUserMovies.contains(movieId)){
                    sameMoviesCount++;
                }
            }

            if((currentUserMoviesCount % 2 == 0 && sameMoviesCount >= currentUserMoviesCount / 2)
                    || (currentUserMoviesCount % 2 != 0 && sameMoviesCount >= currentUserMoviesCount / 2 + 1)){
                usersSet.add(user);
            }
        }

        return usersSet;
    }

    public String recommendMovie(User currentUser) {
        Set<User> usersWithSameInterests = getUsersWithSameInterests(currentUser);
        Map<Integer, Integer> moviesAllView = calculateMoviesAllViews(currentUser, usersWithSameInterests);

        return getRecommendedMovie(moviesAllView);
    }

    private Map<Integer, Integer> calculateMoviesAllViews(User currentUser, Set<User> usersWithSameInterests){
        HashMap<Integer, Integer> moviesAllViews = new HashMap<>();
        Set<Integer> currentUserMovies = currentUser.getViews().keySet();

        for(User user : usersWithSameInterests){
            for(Integer movieId : user.getViews().keySet()){
                if(!currentUserMovies.contains(movieId)){
                    int allViewsCount = moviesAllViews.getOrDefault(movieId, 0);
                    allViewsCount += user.getViews().get(movieId);
                    moviesAllViews.put(movieId, allViewsCount);
                }
            }
        }

        return moviesAllViews;
    }

    private String getRecommendedMovie(Map<Integer, Integer> moviesAllViews) {
        if(moviesAllViews.keySet().size() == 0){
            return "-";
        }

        int maxViews = 0;
        int movieId = 0;

        for(Map.Entry<Integer, Integer> movieEntry : moviesAllViews.entrySet()){
            if(movieEntry.getValue() > maxViews){
                maxViews = movieEntry.getValue();
                movieId = movieEntry.getKey();
            }
        }

        return movies.get(movieId).getTitle();
    }

}



