package advanced;

import homework.Movie;
import homework.MovieDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieListDAO {

    private final MovieDAO movieDAO = new MovieDAO();

    public void create(MovieList list) throws SQLException {
        String sql = "INSERT INTO movie_lists (name) VALUES (?) RETURNING id, created_at";
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, list.getName());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    list.setId(rs.getInt("id"));
                    list.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                }
            }
            con.commit();
        }
    }

    public MovieList findById(int id) throws SQLException {
        String sql = "SELECT id, name, created_at FROM movie_lists WHERE id = ?";
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    public List<MovieList> findAll() throws SQLException {
        String sql = "SELECT id, name, created_at FROM movie_lists ORDER BY created_at DESC";
        List<MovieList> lists = new ArrayList<>();
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lists.add(mapRow(rs));
            }
        }
        return lists;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM movie_lists WHERE id = ?";
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            con.commit();
            return affected > 0;
        }
    }

    public void addMovie(MovieList list, Movie movie) throws SQLException {
        String sql = "INSERT INTO movie_list_entries (list_id, movie_id) VALUES (?, ?)";
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, list.getId());
            ps.setInt(2, movie.getId());
            ps.executeUpdate();
            con.commit();
            list.addMovie(movie);
        }
    }

    private MovieList mapRow(ResultSet rs) throws SQLException {
        return new MovieList(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}