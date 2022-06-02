/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_control;

import java.util.ArrayList;
import view.ChessView;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class Piece implements PieceInterfaces {

    ArrayList<Piece> linkedPieces;
    private int piece_x, piece_y;
    private int x;
    private int y;
    private boolean isInvert;
    private String pieceName;

    // Piece constructor
    public Piece(int piece_x, int piece_y, boolean isInvert, String pieceName, ArrayList<Piece> linkedPieces, int ratio) {
        this.piece_x = piece_x;
        this.piece_y = piece_y;
        x = piece_x * ratio;
        y = piece_y * ratio;
        this.isInvert = isInvert;
        this.linkedPieces = linkedPieces;
        this.pieceName = pieceName;
        linkedPieces.add(this);
    }

    //getting Piece's specific inputs with ratio.
    @Override
    public void move(int pos_X, int pos_Y, int ratio) {
        try {
            int fixed_X = pos_X * ratio;
            int fixed_Y = pos_Y * ratio;
            if (pos_X != 0 && pos_Y != 0 && ratio != 0) {
                if (ChessView.getPiece(fixed_X, fixed_Y, 62) != null) {
                    if (!ChessView.getPiece(fixed_X, fixed_Y, 62).isInvert && this.isInvert) {
                        ChessView.getPiece(fixed_X , fixed_Y, 62).removePiece();
                    } else {
                        x = this.piece_x * ratio;
                        y = this.piece_y * ratio;
                        return;
                    }
                }
            }
            revert(true, pos_X, pos_Y,fixed_X,fixed_Y);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Miss placed values and cant move.");
        }
    }
    // checking if ai decision if it wants to revert.
    public void revert(boolean isRevert, int x, int y, int fixed_X, int fixed_Y){
        if(isRevert){
            this.piece_x = x;
            this.piece_y = y;
            this.x = fixed_X;
            this.y = fixed_Y;
        }else{
            System.out.println("Revert:" + isRevert);
        }
    }
    //getting Piece's selected information
    @Override
    public void getPieceInfo() {
        System.out.println("Piece x: " + this.x + ",Piece y: " + this.y + ",Piece name:" + this.getPieceName());
    }

    //removing Piece's selecetd position
    @Override
    public void removePiece() {
        this.linkedPieces.remove(this);
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public boolean isIsInvert() {
        return isInvert;
    }

    public void setIsInvert(boolean isInvert) {
        this.isInvert = isInvert;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPiece_y() {
        return piece_y;
    }

    public void setPiece_y(int piece_y) {
        this.piece_y = piece_y;
    }

    public int getPiece_x() {
        return piece_x;
    }

    public void setPiece_x(int piece_x) {
        this.piece_x = piece_x;
    }

}
