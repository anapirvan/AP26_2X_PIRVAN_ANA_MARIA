package compulsory;

import java.sql.*;

public class GenreDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select id from genres where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "select name from genres where id='" + id + "'")) {
            return resultSet.next() ? resultSet.getString(1) : null;
        }
    }
}
