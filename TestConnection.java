import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
 
public class TestConnection { 
    public static void main(String[] args) { 
        String url = "jdbc:sybase:Tds:10.0.14.130:2649/pratico9"; 
        String username = "dbo"; 
        String password = "dircri"; 
        try { 
            Class.forName("com.sybase.jdbc3.jdbc.SybDriver"); 
            Connection conn = DriverManager.getConnection(url, username, password); 
            System.out.println("Conexao bem-sucedida!"); 
            conn.close(); 
        } catch (ClassNotFoundException e) { 
            System.out.println("Driver nao encontrado: " + e.getMessage()); 
        } catch (SQLException e) { 
            System.out.println("Erro de conexao: " + e.getMessage()); 
        } 
    } 
} 
