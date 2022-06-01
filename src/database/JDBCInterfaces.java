/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import view.ChessView;

/**
 *
 * @author quokka
 */
public interface JDBCInterfaces {
        public void startDB();
        public boolean isTableExist(String tableName);
        public void checkDataElements();
        public void saveGameStatus(String userName, int score);
        public void clearLog();
        public chessData checkPlayerName(String username, String password, ChessView view);
}
