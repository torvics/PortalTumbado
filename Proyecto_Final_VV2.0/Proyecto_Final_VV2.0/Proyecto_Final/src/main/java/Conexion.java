import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    final String DB_URL = "jdbc:mysql://localhost/desarrollo?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "";
    private Connection con;

    public Connection getConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
       con = (Connection) DriverManager.getConnection(this.DB_URL, this.PASSWORD, this.PASSWORD);


        }catch (SQLException e){

        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
