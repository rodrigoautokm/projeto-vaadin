package com.exemplo;

import java.sql.*;

public class SybaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:sybase:Tds:10.0.14.130:2649/pratico9";
        String username = "dbo";
        String password = "dircri";

        try {
            // <- AQUI O NOME CERTO:
            Class.forName("com.sybase.jdbc.SybDriver");
            System.out.println("Driver carregado com sucesso!");

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Conexão estabelecida com sucesso!");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GETDATE()");
                while (rs.next()) {
                    System.out.println("Data e hora atual do banco: " + rs.getString(1));
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }
}
