/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class chessData {

    //chess game data
    private boolean loginFlag = false;
    private boolean quitFlag = false;
    private int currentScore = 0;
        public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public boolean isQuitFlag() {
        return quitFlag;
    }

    public void setQuitFlag(boolean quitFlag) {
        this.quitFlag = quitFlag;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
