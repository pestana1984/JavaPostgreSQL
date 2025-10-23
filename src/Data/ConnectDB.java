package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static String hostname;
    private static String user;
    private static String password;

    public ConnectDB(String hostname, String user, String password, String db) {

        this.hostname = "jdbc:postgresql://"+ hostname + ":5432/" + db; //URL DE CONEXÃO
        this.user = user;
        this.password = password;

    }

    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(hostname, user, password);
            return connection;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.err.println(e.getStackTrace());
            return null;
        }
    }

}
