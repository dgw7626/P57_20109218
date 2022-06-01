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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.ChessView;
//import java.sql.logging.Level;
//import java.sql.logging.Logger;

public class JDBC {

    Connection conn = null;
    String url = "jdbc:derby:gameData;create=true";
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
            checkDataElements();
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
            checkDataElements();
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public void checkDataElements(){
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USERINFO");
            while(rs.next()){
                int score = rs.getInt("score");
                String userName = rs.getString("username");
                System.out.println("USER NAME:" + userName + " ,PLAYER SCORE:" +score);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void saveGameStatus(String userName, int score){
        Statement statement;
        try{
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE USERINFO SET score=" + score +"WHERE username='" + userName +"'");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void clearLog(){
            try {
            conn = DriverManager.getConnection(url, dbUserName, dbPassword);
            Statement statement = conn.createStatement();
            String tableName = "USERINFO";
            if (!isTableExist(tableName)) {
                statement.executeUpdate("DROP TABLE " + tableName);
            }
            checkDataElements();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public chessData checkPlayerName(String username, String password, ChessView view){
         chessData data = new chessData(); // Initialize an instance of Data.
        // ChessView view = new ChessView();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username, password, score FROM USERINFO "
                    + "WHERE username = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                System.out.println("USER Password:" + pass);
                System.out.println("USER FOUND: "+username);
                /**
                 * If the username exists in the USERINFO table, and the
                 * password is correct, change the value of relating attributes
                 * of data. Otherwise, keep loginFlag as false.
                 */
                if (password.compareTo(pass) == 0) {
                    data.setCurrentScore(rs.getInt("score")); 
                    data.setLoginFlag(true);
                } else {
                    data.setLoginFlag(false);
                }
            } else {
                /**
                 * If the username does not exist in the USERINFO table, then
                 * create a new account by using the inputted username and
                 * password.
                 */
                JOptionPane.showMessageDialog(view, "User Name: " + username + " has been created.", "Account Created!",JOptionPane.PLAIN_MESSAGE);
                statement.executeUpdate("INSERT INTO USERINFO "
                        + "VALUES('" + username + "', '" + password + "', 0)");
                data.setCurrentScore(0);
                data.setLoginFlag(true);
            }
        } catch (SQLException ex) {
         //   JOptionPane.showMessageDialog(view, "data dosent exist", "DATA ERROR",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data; //Back to checkName() of Model.java.
    }
}
