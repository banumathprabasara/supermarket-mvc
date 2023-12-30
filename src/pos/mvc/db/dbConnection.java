/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author banumathprabasara
 */
public class dbConnection {
    private static dbConnection dbConnection;
    private Connection connection;
    
    private  dbConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperMarket","root","password");
         } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static dbConnection getInstance(){
        if (dbConnection==null) {
            dbConnection = new dbConnection();
        }
        return dbConnection;
    }
    public Connection geConnection(){
        return connection;
    }
}
