package Util;

import java.sql.*;

public class ConexionSQLite {
    
     private static final String URL = "jdbc:sqlite:db/MovUTPL.db";
    
    public static Connection conectar(){
    
        try {
           return DriverManager.getConnection(URL);
            } catch (SQLException e) {
                System.out.println("Error de conexion: " + e.getMessage());
                return null;
            }
    
    }

}
    

