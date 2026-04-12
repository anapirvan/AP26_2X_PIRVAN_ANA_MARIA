package homework;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GenreDAO genreDAO = new GenreDAO();
        ActorDAO actorDAO = new ActorDAO();
        MovieDAO movieDAO = new MovieDAO();

        try {
            Genre genre1 = new Genre("Action");
            Genre genre2 = new Genre("Drama");
            genreDAO.create(genre1);
            genreDAO.create(genre2);

            Genre foundGenre = genreDAO.findByName("Action");
            System.out.println("findByName: " + foundGenre);
            System.out.println("findAll: " + genreDAO.findAll());

            foundGenre.setName("Sci-Fi");
            genreDAO.update(foundGenre);
            System.out.println("dupa update: " + genreDAO.findById(foundGenre.getId()));

            Genre drama = genreDAO.findByName("Drama");
            genreDAO.delete(drama.getId());
            System.out.println("dupa delete: " + genreDAO.findAll());
            Genre genre3=new Genre("Drama");
            genreDAO.create(genre3);

            Actor actor1 = new Actor("Leonardo", "DiCaprio", "1974-11-11", "American");
            Actor actor2 = new Actor("Cillian", "Murphy", "1976-05-25", "Irish");
            actorDAO.create(actor1);
            actorDAO.create(actor2);

            Actor foundActor = actorDAO.findByName("Leonardo", "DiCaprio");
            System.out.println("findByName: " + foundActor);
            System.out.println("findAll: " + actorDAO.findAll());

            foundActor.setNationality("American-Italian");
            actorDAO.update(foundActor);
            System.out.println("dupa update: " + actorDAO.findById(foundActor.getId()));

            Actor murphy = actorDAO.findByName("Cillian", "Murphy");
            actorDAO.delete(murphy.getId());
            System.out.println("dupa delete: " + actorDAO.findAll());

            Genre sciFi = genreDAO.findByName("Sci-Fi");

            Movie movie1 = new Movie("Inception", "2010-07-16", 148, 9, sciFi);
            Movie movie2 = new Movie("Interstellar", "2014-11-07", 169, 9, sciFi);
            movieDAO.create(movie1);
            movieDAO.create(movie2);

            Movie foundMovie = movieDAO.findByTitle("Inception");
            System.out.println("findByTitle: " + foundMovie);
            System.out.println("findAll: " + movieDAO.findAll());

            foundMovie.setScore(10);
            movieDAO.update(foundMovie);
            System.out.println("dupa update: " + movieDAO.findById(foundMovie.getId()));

            new ReportGenerator().generate();

        } catch (SQLException | IOException | TemplateException e) {
            System.err.println("Eroare: " + e.getMessage());
        } finally {
            Database.closePool();
        }
    }
}