package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public void create(Movie movie) throws SQLException {
        String sql = "INSERT INTO movies (title, release_date, duration, score, genre_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDate(2, java.sql.Date.valueOf(movie.getReleaseDate()));
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.setInt(4, movie.getScore());
            preparedStatement.setInt(5, movie.getGenre().getId());
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }

    public Movie findById(int id) throws SQLException {
        String sql = """
                SELECT m.id, m.title, m.release_date, m.duration, m.score, g.id, g.name 
                FROM movies m
                JOIN genres g ON m.genre_id = g.id
                WHERE m.id = ?
                """;
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            } else {
                return null;
            }
        }
    }

    public Movie findByTitle(String title) throws SQLException {
        String sql = """
                SELECT m.id, m.title, m.release_date, m.duration, m.score, g.id, g.name
                FROM movies m
                JOIN genres g ON m.genre_id = g.id
                WHERE m.title = ?
                """;
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            } else {
                return null;
            }
        }
    }

    public List<Movie> findAll() throws SQLException {
        String sql = """
                SELECT m.id, m.title, m.release_date, m.duration, m.score, g.id, g.name
                FROM movies m
                JOIN genres g ON m.genre_id = g.id
                ORDER BY m.id
                """;
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movies.add(mapRow(resultSet));
            }
        }
        return movies;
    }

    public boolean update(Movie movie) throws SQLException {
        String sql = "UPDATE movies SET title = ?, release_date = ?, duration = ?, score = ?, genre_id = ? WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setDate(2, java.sql.Date.valueOf(movie.getReleaseDate()));
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.setInt(4, movie.getScore());
            preparedStatement.setInt(5, movie.getGenre().getId());
            preparedStatement.setInt(6, movie.getId());
            int affected = preparedStatement.executeUpdate();
            connection.commit();
            return affected > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affected = preparedStatement.executeUpdate();
            connection.commit();
            return affected > 0;
        }
    }

    private Movie mapRow(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre(resultSet.getInt(6), resultSet.getString(7));
        return new Movie(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDate("release_date").toString(), resultSet.getInt("duration"), resultSet.getInt("score"), genre);
    }
}