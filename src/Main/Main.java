package Main;

import Data.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void GetUsers(ConnectDB db){
        try{
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");

            while(rs.next()){
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("usuario"));
                System.out.println(rs.getString("senha"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void InsertUser(ConnectDB db){
        try{
            String sql =  "INSERT INTO usuarios (id, usuario, senha) VALUES (?, ?, ?)";

            Scanner sc = new Scanner(System.in);
            System.out.println("Informe o ID:");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Informe o usuario:");
            String usuario = sc.nextLine();
            System.out.println("Informe a senha:");
            String senha = sc.nextLine();

            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, usuario);
            ps.setString(3, senha);

            int affectedRows = ps.executeUpdate();
            if(affectedRows != 0){
                System.out.println("Usuário inserido com Sucesso!");
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        ConnectDB db = new ConnectDB("dpg-d3rcb88gjchc73cpjlug-a.oregon-postgres.render.com",
                "testjava", "Q0buWnbkqDMFmYEHZXXMtSHL54NHrNZ9", "testjava_gz5z");

        GetUsers(db);
        InsertUser(db);
        GetUsers(db);
    }
}
