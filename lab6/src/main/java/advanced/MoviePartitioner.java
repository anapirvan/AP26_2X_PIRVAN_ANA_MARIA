package advanced;

import homework.Actor;
import homework.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviePartitioner {

    private final MovieDAO movieDAO = new MovieDAO();

    public List<MovieList> partition() throws SQLException {
        List<Movie> movies = movieDAO.findAllWithActors();

        movies.sort((a, b) -> b.getActors().size() - a.getActors().size());

        List<MovieList> lists = new ArrayList<>();

        for (Movie movie : movies) {
            boolean placed = false;
            for (MovieList list : lists) {
                if (!hasConflict(movie, list)) {
                    list.addMovie(movie);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                MovieList newList = new MovieList("Partitie " + (lists.size() + 1));
                newList.addMovie(movie);
                lists.add(newList);
            }
        }

        return lists;
    }

    private boolean hasConflict(Movie movie, MovieList list) {
        for (Movie other : list.getMovies()) {
            for (Actor actor : movie.getActors()) {
                if (other.getActors().contains(actor)) return true;
            }
        }
        return false;
    }
}