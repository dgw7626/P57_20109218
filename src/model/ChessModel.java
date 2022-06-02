/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.JDBC;
import database.chessData;
import java.util.Observable;
import java.util.Random;
import javax.swing.JOptionPane;
import view.ChessView;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class ChessModel extends Observable implements ChessModelInterfaces{
    public JDBC db;
    public chessData data;
    public String userName; 
    
    // Chess model constructor
    public ChessModel(){
        this.db = new JDBC();
        this.db.startDB();
    }
    
    //Checking data from mysql database
    @Override
    public void checkPlayerDBName(String username, String password, ChessView view){
        this.userName = username;
        this.data = this.db.checkPlayerName(username, password, view);
        if(data.isLoginFlag()){
            
            System.out.println("active");
          
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    // saving game with random score values.
    @Override
    public void saveGameStatus(){
        Random random = new Random();
        this.data.setCurrentScore(random.nextInt(100));
        System.out.println("saved player data:" +this.data.getCurrentScore());
        this.db.saveGameStatus(userName, this.data.getCurrentScore());
        this.data.setQuitFlag(true);
        this.setChanged();
        this.notifyObservers(this.data);
    }
    // clear data log
    @Override
    public void clearDataLog() {
        this.db.clearLog();
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
}
