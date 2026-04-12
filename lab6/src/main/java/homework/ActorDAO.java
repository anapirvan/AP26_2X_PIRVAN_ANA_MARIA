package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

    public void create(Actor actor) throws SQLException {
        String sql = "INSERT INTO actors (first_name, last_name, birth_date, nationality) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, actor.getFirstName());
            preparedStatement.setString(2, actor.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(actor.getBirthDate()));
            preparedStatement.setString(4, actor.getNationality());
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }

    public Actor findById(int id) throws SQLException {
        String sql = "SELECT id, first_name, last_name, birth_date, nationality FROM actors WHERE id = ?";
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

    public Actor findByName(String firstName, String lastName) throws SQLException {
        String sql = "SELECT id, first_name, last_name, birth_date, nationality FROM actors WHERE first_name = ? AND last_name = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            } else {
                return null;
            }
        }
    }

    public List<Actor> findAll() throws SQLException {
        String sql = "SELECT id, first_name, last_name, birth_date, nationality FROM actors ORDER BY id";
        List<Actor> actors = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actors.add(mapRow(resultSet));
            }
        }
        return actors;
    }

    public boolean update(Actor actor) throws SQLException {
        String sql = "UPDATE actors SET first_name = ?, last_name = ?, birth_date = ?, nationality = ? WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, actor.getFirstName());
            preparedStatement.setString(2, actor.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(actor.getBirthDate()));
            preparedStatement.setString(4, actor.getNationality());
            preparedStatement.setInt(5, actor.getId());
            int affected = preparedStatement.executeUpdate();
            connection.commit();
            return affected > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM actors WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affected = preparedStatement.executeUpdate();
            connection.commit();
            return affected > 0;
        }
    }

    public Actor mapRow(ResultSet resultSet) throws SQLException {
        return new Actor(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getDate("birth_date").toString(), resultSet.getString("nationality")
        );
    }
}