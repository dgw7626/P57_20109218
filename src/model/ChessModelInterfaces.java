/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.ChessView;

/**
 *
 * @author quokka
 */
public interface ChessModelInterfaces {
    public void checkPlayerDBName(String username, String password, ChessView view);
    public void saveGameStatus();
    public void clearDataLog();
}
