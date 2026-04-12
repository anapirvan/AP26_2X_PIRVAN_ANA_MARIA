package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

    public void create(Genre genre) throws SQLException {
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }

    public Genre findById(int id) throws SQLException {
        String sql = "SELECT id, name FROM genres WHERE id = ?";
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

    public Genre findByName(String name) throws SQLException {
        String sql = "SELECT id, name FROM genres WHERE name = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            } else {
                return null;
            }
        }
    }

    public List<Genre> findAll() throws SQLException {
        String sql = "SELECT id, name FROM genres ORDER BY id";
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(mapRow(resultSet));
            }
        }
        return genres;
    }

    public boolean update(Genre genre) throws SQLException {
        String sql = "UPDATE genres SET name = ? WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setInt(2, genre.getId());
            int affected = preparedStatement.executeUpdate();
            connection.commit();
            return affected > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM genres WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affected = preparedStatement.executeUpdate();
            connection.commit();
            return affected > 0;
        }
    }

    private Genre mapRow(ResultSet resultSet) throws SQLException {
        return new Genre(resultSet.getInt("id"), resultSet.getString("name"));
    }
}