package ru.croc.javaschool2024.soloveva.task11;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
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
        } catch (IncorrectMovieException | IncorrectHistoryException e){
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
      /*  Scanner sc = new Scanner(System.in);
        HashSet<Integer> currentUserMovies = new HashSet<>();
        {
            String[] parts = sc.nextLine().split(",");
            for (String part : parts) {
                int movieId = Integer.parseInt(part);
                currentUserMovies.add(movieId);
            }
        }

        String historyFileName = "src/ru/croc/javaschool2024/soloveva/task11/resources/history.txt";
        String moviesFileName = "src/ru/croc/javaschool2024/soloveva/task11/resources/movies.txt";

        HashMap<Integer, String> movies = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Integer>> usersMovies = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(moviesFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length >= 2) {
                    int number = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    movies.put(number, title);
                } else {
                    System.out.println("Некорректная строка: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }

        Set<Integer> moviesSet = movies.keySet();
        Set<Integer> unwatchedSet = new HashSet<>();

        for(Integer movieId : moviesSet){
            if(!currentUserMovies.contains(movieId)){
                unwatchedSet.add(movieId);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(historyFileName))) {
            String line;
            int userId = 0;
            while ((line = reader.readLine()) != null) {
                userId++;
                HashMap<Integer, Integer> views = new HashMap<>();
                String[] parts = line.split(",");

                for(String part : parts){
                    int movieId = Integer.parseInt(part);

                    int viewsCount = views.getOrDefault(movieId, 0);
                    views.put(movieId, viewsCount + 1);
                }

                usersMovies.put(userId, views);
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }

        Set<Integer> usersSet = new HashSet<>();
        int currentUserMoviesCount = currentUserMovies.size();

        for(Integer userId : usersMovies.keySet()){
            HashMap<Integer, Integer> userViews = usersMovies.get(userId);

            int userMoviesCount = 0;

            for(Integer movieId : userViews.keySet()){
                if(currentUserMovies.contains(movieId)){
                    userMoviesCount++;
                }
            }

            if((currentUserMoviesCount % 2 == 0 && userMoviesCount >= currentUserMoviesCount / 2)
                    || (currentUserMoviesCount % 2 != 0 && userMoviesCount >= currentUserMoviesCount / 2 + 1)){
                usersSet.add(userId);
            }
        }

        HashMap<Integer, Integer> moviesAllViews = new HashMap<>();

        for(Integer userId : usersSet){
            HashMap<Integer, Integer> userViews = usersMovies.get(userId);

            for(Integer movieId : userViews.keySet()){
                if(!currentUserMovies.contains(movieId)){
                    int allViewsCount = moviesAllViews.getOrDefault(movieId, 0);
                    allViewsCount += userViews.get(movieId);
                    moviesAllViews.put(movieId, allViewsCount);
                }
            }
        }

        if(moviesAllViews.keySet().size() == 0){
            System.out.println("-");
            return;
        }

        int maxViews = 0;
        int movieId = 0;

        for(Map.Entry<Integer, Integer> movieEntry : moviesAllViews.entrySet()){
            if(movieEntry.getValue() > maxViews){
                maxViews = movieEntry.getValue();
                movieId = movieEntry.getKey();
            }
        }
        System.out.println(movies.get(movieId));
    }
} /*/
