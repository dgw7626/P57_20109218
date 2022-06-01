/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.JDBC;
import database.chessData;
import java.util.Observable;
import javax.swing.JOptionPane;
import view.ChessView;

/**
 *
 * @author Admin
 */
public class ChessModel extends Observable{
    public JDBC db;
    public chessData data;
    
    public String userName; // restoring username;
    
    
    public ChessModel(){
        this.db = new JDBC();
        this.db.startDB();
    }
    
    public void checkPlayerDBName(String username, String password, ChessView view){
        this.userName = username; // store name;
        this.data = this.db.checkPlayerName(username, password, view);
        if(data.isLoginFlag()){
            
            System.out.println("active");
          
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void saveGameStatus(){
        this.db.saveGameStatus(userName, this.data.getCurrentScore());
        this.data.setQuitFlag(true);
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
}
