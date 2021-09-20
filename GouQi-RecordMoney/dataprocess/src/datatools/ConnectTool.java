package datatools;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTool {
    static URL resource = ConnectTool.class.getResource("/datatools/caifu.db");
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite::resource:" + resource.toExternalForm());
//            System.out.println(resource.toExternalForm());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
