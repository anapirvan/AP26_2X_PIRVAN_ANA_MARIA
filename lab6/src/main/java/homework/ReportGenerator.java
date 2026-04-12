package homework;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    private static final String OUTPUT = "movie_report.html";

    public void generate() throws SQLException, IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_33);
        configuration.setDirectoryForTemplateLoading(new File("C:/Users/User-PC/Desktop/Java/lab6/src/main/resources"));
        configuration.setDefaultEncoding("UTF-8");

        List<Map<String, Object>> movies = loadMovies();

        Map<String, Object> model = new HashMap<>();
        model.put("movies", movies);

        Template template = configuration.getTemplate("template.ftl");
        try (FileWriter writer = new FileWriter(OUTPUT)) {
            template.process(model, writer);
        }

    }

    private List<Map<String, Object>> loadMovies() throws SQLException {
        String sql = "SELECT id, title, release_date, duration, score, genre FROM movie_report";
        List<Map<String, Object>> movies = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> movie = new HashMap<>();
                movie.put("id", rs.getInt("id"));
                movie.put("title", rs.getString("title"));
                movie.put("releaseDate", rs.getString("release_date"));
                movie.put("duration", rs.getInt("duration"));
                movie.put("score", rs.getInt("score"));
                movie.put("genre", rs.getString("genre"));
                movies.add(movie);
            }
        }
        return movies;
    }
}
