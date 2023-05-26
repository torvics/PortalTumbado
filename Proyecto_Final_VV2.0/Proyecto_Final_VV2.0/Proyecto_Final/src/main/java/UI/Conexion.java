package UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    final String DB_URL = "jdbc:mysql://localhost/desarrollo?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "";
    //public Connection con;




        Connection conn = DriverManager.getConnection (DB_URL, USERNAME, PASSWORD);

    public Conexion() throws SQLException {
    }

    public Connection getConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
       conn = (Connection) DriverManager.getConnection(this.DB_URL, this.PASSWORD, this.PASSWORD);


        }catch (SQLException e){

        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
