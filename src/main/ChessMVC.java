/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.ChessController;
import model.ChessModel;
import view.ChessView;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class ChessMVC {

   // main chess program Model, View, Controller
    public static void main(String[] args) {
        ChessView view = new ChessView();
        ChessModel model = new ChessModel();
        ChessController controller = new ChessController(view, model);
        model.addObserver(view); // build connection between the view and the model.
    }
    
   
}
