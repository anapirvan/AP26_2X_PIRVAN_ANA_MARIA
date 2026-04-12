package homework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Movie {
    private int id;
    private String title, releaseDate;
    private int duration, score;
    private Genre genre;
    private List<Actor> actors = new ArrayList<>();

    public Movie(int id, String title, String releaseDate, int duration, int score, Genre genre) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genre = genre;
    }

    public Movie(String title, String releaseDate, int duration, int score, Genre genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genre = genre;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", duration=" + duration +
                ", score=" + score +
                ", genre=" + genre +
                ", actors=" + actors +
                '}';
    }
}
