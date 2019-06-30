/*
 * Connect To Xampp Server and create new database
 */
package createtoxamppserverandcreatenewdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Genuine
 */
public class CreateToXamppServerAndCreateNewDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String serverPath = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String Password = "";
        Connection conn = null;
        boolean checkIfdDatabaseExixts = false;
        String databaseName = "TestDatabase";
        String IfdDatabaseExixtsSQLQuery = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '"+ databaseName+"'";
        Statement stmt = null;
        ResultSet rs = null;
        String createNewDatabase = "CREATE DATABASE IF NOT EXISTS " + databaseName+"" ;
        try {
            Class.forName(jdbcDriver);
            System.out.println("Jdbc Driver loaded successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println("Jdbc Driver Failed To load successfully");
            System.out.println(ex.getMessage());        }
        
        try {
            conn = DriverManager.getConnection(serverPath, username, Password);
            System.out.println("Connected To Server Successfully");
        } catch (SQLException ex) {
            System.out.println("Failed To Connect To Server Successfully");
            System.out.println(ex.getMessage());        }
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(IfdDatabaseExixtsSQLQuery);
            if (rs.next()) {
                System.out.println("Database Found");
                checkIfdDatabaseExixts = true;
            }
            
            if (!checkIfdDatabaseExixts) {
                System.out.println("Database Doesn't Exist");
                System.out.println("Creating New Database");
                int newDatabaseCreated  = stmt.executeUpdate(createNewDatabase);
                if (newDatabaseCreated > 0) {
                    System.out.println("New Database Created");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
        
        
    }
    
}
