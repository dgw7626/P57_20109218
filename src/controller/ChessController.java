/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ChessModel;
import view.ChessView;

public class ChessController implements ActionListener{
    public ChessView view;
    public ChessModel model;

    public ChessController(ChessView view,ChessModel model){
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Login":
                System.out.println("Hello");
                String username = this.view.getUserName().getText();
                String password = this.view.getUserPass().getText();
                if(username != null && password != null){
                    if(username.equalsIgnoreCase("") && password.equalsIgnoreCase("")){
                         JOptionPane.showMessageDialog(view, "Please retype", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                          this.model.checkPlayerDBName(username, password, this.view);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(view, "Please retype", "Input Error",JOptionPane.ERROR_MESSAGE);
                }
            
                break;
            case "Next":
                System.out.println("next");
                break;
            case "Back":
                this.view.chessLogin();
                break;
            case "Clear Log":
                this.model.clearDataLog();
                break;
            case "Player Log":
                String logName = this.model.userName;
                JOptionPane.showMessageDialog(view,  "Player Name: " +  logName,"Player Information",JOptionPane.PLAIN_MESSAGE);
                break;
            case "Player VS AI":
                this.view.inGame();
                break;
            case "Save":
                //this.model.saveGameStatus();
                break;
            case "Exit":
                //this.model.saveGameStatus();
                System.exit(0);
                break;
        }
    }

}
