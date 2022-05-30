/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ChessModel;
import view.ChessView;

/**
 *
 * @author Admin
 */
public class ChessController implements ActionListener{
    public ChessView view;
    public ChessModel model;

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Log in":
                break;
            case "Next":
                break;
            case "Quit":
                break;
        }
    }
}
