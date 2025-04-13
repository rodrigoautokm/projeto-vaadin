echo import java.sql.Connection; > TestConnection.java
echo import java.sql.DriverManager; >> TestConnection.java
echo import java.sql.SQLException; >> TestConnection.java
echo. >> TestConnection.java
echo public class TestConnection { >> TestConnection.java
echo     public static void main(String[] args) { >> TestConnection.java
echo         String url = "jdbc:sybase:Tds:10.0.14.130:2649/pratico9"; >> TestConnection.java
echo         String username = "dbo"; >> TestConnection.java
echo         String password = "dircri"; >> TestConnection.java
echo         try { >> TestConnection.java
echo             Class.forName("com.sybase.jdbc3.jdbc.SybDriver"); >> TestConnection.java
echo             Connection conn = DriverManager.getConnection(url, username, password); >> TestConnection.java
echo             System.out.println("Conexao bem-sucedida!"); >> TestConnection.java
echo             conn.close(); >> TestConnection.java
echo         } catch (ClassNotFoundException e) { >> TestConnection.java
echo             System.out.println("Driver nao encontrado: " + e.getMessage()); >> TestConnection.java
echo         } catch (SQLException e) { >> TestConnection.java
echo             System.out.println("Erro de conexao: " + e.getMessage()); >> TestConnection.java
echo         } >> TestConnection.java
echo     } >> TestConnection.java
echo } >> TestConnection.java