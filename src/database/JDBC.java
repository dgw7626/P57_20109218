/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Admin
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.logging.Level;
//import java.sql.logging.Logger;

public class JDBC {

    Connection conn = null;
    String url = "";
    String dbUserName = "chess";
    String dbPassword = "big";

    public void startDB() {
        try {
            conn = DriverManager.getConnection(url, dbUserName, dbPassword);
            Statement statement = conn.createStatement();
            String tableName = "USERINFO";
            if (!isTableExist(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + "(username VARCHAR(12), password VARCHAR(12), score INT)");
            }
            
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isTableExist(String tableName) {
        boolean flag = false;
        try {
            System.out.println("[checking tables...]");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, null, null); // types

            while (rs.next()) {
                String currentTable = rs.getString("TABLE_NAME");
                if (currentTable.compareToIgnoreCase(tableName) == 0) {
                    System.out.println("Current Table: " + currentTable + "[Active]");
                    flag = true;
                }
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
