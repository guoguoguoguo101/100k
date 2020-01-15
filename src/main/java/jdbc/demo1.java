package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class demo1 {

    public static void main(String[] args) throws Exception {
        String JDBC_URL = "jdbc:mysql://localhost:3306/student";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "root";

        Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from students");
        while (set.next()){
            String name = set.getString(2);
            System.out.println(name);
        }
        connection.close();


    }
}
