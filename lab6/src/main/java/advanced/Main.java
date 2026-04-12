package advanced;

import homework.Movie;
import homework.MovieDAO;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database.getInstance();

        MovieDAO movieDAO = new MovieDAO();
        MovieListDAO listDAO = new MovieListDAO();

        try {
            MovieImporter importer = new MovieImporter("movies_metadata.csv");
            Movie movie;
            int count = 0;
            while ((movie = importer.importMovie()) != null && count < 50) {
                try {
                    movieDAO.create(movie);
                    count++;
                    System.out.println("Imported: " + movie.getTitle());
                } catch (SQLException e) {
                    System.err.println("Skipping: " + movie.getTitle());
                }
            }

            MovieList favorites = new MovieList("Favorites");
            listDAO.create(favorites);

            Movie toyStory = movieDAO.findByTitle("Toy Story");
            Movie jumanji = movieDAO.findByTitle("Jumanji");

            listDAO.addMovie(favorites, toyStory);
            listDAO.addMovie(favorites, jumanji);


            MoviePartitioner partitioner = new MoviePartitioner();
            List<MovieList> partitions = partitioner.partition();

            System.out.println("\nPartitii");
            for (MovieList list : partitions) {
                System.out.println(list.getName() + " - " + list.getMovies().size() + " filme");
            }
        } catch (SQLException e) {
            System.out.println("Eroare: " + e.getMessage());
        } finally {
            Database.closePool();
        }
    }
}