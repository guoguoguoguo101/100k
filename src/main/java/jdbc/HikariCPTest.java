package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPTest {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/student");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.addDataSourceProperty("connectionTimeout","1000");
        hikariConfig.addDataSourceProperty("idLeTimeout","60000");
        hikariConfig.addDataSourceProperty("maximumPoolSize","10");
        DataSource ds = new HikariDataSource(hikariConfig);
        try {
            Connection con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
