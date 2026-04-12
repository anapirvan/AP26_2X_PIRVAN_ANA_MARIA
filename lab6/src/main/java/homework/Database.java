package homework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/filme";
    private static final String USER = "postgres";
    private static final String PASSWORD = "010605";

    private static HikariDataSource dataSource;
    private static  Database database=null;

    private Database() {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(URL);
        configuration.setUsername(USER);
        configuration.setPassword(PASSWORD);

        configuration.setAutoCommit(false);

        dataSource = new HikariDataSource(configuration);
    }

    public static Database getInstance(){
        if(database==null){
            database=new Database();
        }
        return database;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
