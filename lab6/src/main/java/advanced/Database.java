package advanced;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/filme";
    private static final String USER = "postgres";
    private static final String PASSWORD = "010605";

    private static HikariDataSource dataSource;
    private static Database instance;

    private Database() {
        Flyway flyway = Flyway.configure().dataSource(URL, USER, PASSWORD).locations("classpath:db_migration").cleanDisabled(false).load();
        flyway.clean();
        flyway.migrate();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setAutoCommit(false);
        dataSource = new HikariDataSource(config);
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}