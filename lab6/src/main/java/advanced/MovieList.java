package advanced;

import homework.Movie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class MovieList {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private List<Movie> movies = new ArrayList<>();

    public MovieList(int id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public MovieList(String name) {
        this.name = name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", movies=" + movies +
                '}';
    }
}
