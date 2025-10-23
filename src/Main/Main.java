package Main;

import Data.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void GetAllUsers(ConnectDB db) {
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios ORDER BY id");


            while (rs.next()) {
                System.out.print("Id: " + rs.getInt("id"));
                System.out.print(" Login: " + rs.getString("login"));
                System.out.print(" Senha: " + rs.getString("senha"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void InsertUser(ConnectDB db) {
        try {

            String sql = "INSERT INTO usuarios (id, login, senha) VALUES (?, ?, ?)";

            Scanner sc = new Scanner(System.in);
            System.out.println("Informe o ID:");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Informe o usuario:");
            String login = sc.nextLine();
            System.out.println("Informe a senha:");
            String senha = sc.nextLine();

            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, login);
            ps.setString(3, senha);

            int affectedRows = ps.executeUpdate();
            if (affectedRows != 0) {
                System.out.println("Usuário inserido com Sucesso!");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void FindUser(ConnectDB db){
        try{
            Scanner sc = new Scanner(System.in);
            String sql = "SELECT * FROM usuarios WHERE id = ";

            Statement st = db.getConnection().createStatement();

            System.out.println("Informe o ID:");
            var id = sc.nextLine();

            sql += id;

            ResultSet rs = st.executeQuery(sql);

            if(rs.getRow() == 0){
                System.err.println("Nenhum usuário encontrado com o ID informado!");
            }
            else {
                while (rs.next()) {
                    System.out.println(rs.getInt("id")
                            + " " + rs.getString("login")
                            + " " + rs.getString("senha")
                    );
                }
            }

        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void UpdateUserPassword(ConnectDB db) {
        try {
            Scanner sc = new Scanner(System.in);
            String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";

            System.out.println("Informe o ID do Usuario: ");
            var id = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            System.out.println("Informe a nova senha: ");
            var senha = sc.nextLine();

            ps.setString(1, senha);
            ps.setInt(2, id);

            var affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Não foi encontrado nenhum usuário com o ID informado!");
            }
            else{
                System.out.println("Senha atualizada com sucesso!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void DeleteUser(ConnectDB db) {
        String sql = "DELETE FROM usuarios WHERE id = ?";


        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            Scanner sc = new Scanner(System.in);

            System.out.println("Informe o ID do Usuario: ");
            var id = sc.nextInt();
            sc.nextLine();

            ps.setInt(1, id);
            var affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Nenhum usuário encontrado com o ID informado!");
            }
            else{
                System.out.println("Usuario excluido com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        ConnectDB db = new ConnectDB("dpg-d3rcb88gjchc73cpjlug-a.oregon-postgres.render.com",
                "testjava", "Q0buWnbkqDMFmYEHZXXMtSHL54NHrNZ9", "testjava_gz5z");

        if(db.getConnection() != null)
        {
            System.out.println("Conectado com sucesso!");
        }

        GetAllUsers(db);
        InsertUser(db);
        UpdateUserPassword(db);
        DeleteUser(db);
        GetAllUsers(db);
        FindUser(db);
        GetAllUsers(db);
    }
}
