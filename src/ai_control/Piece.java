/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_control;

import java.util.LinkedList;
import view.ChessView;

/**
 *
 * @author quokka
 */
public class Piece implements PieceInterfaces {

    LinkedList<Piece> linkedPieces;
    private int piece_x, piece_y;
    private int x;
    private int y;
    private boolean isInvert;
    private String pieceName;

    public Piece(int piece_x, int piece_y, boolean isInvert, String pieceName, LinkedList<Piece> linkedPieces, int ratio) {
        this.piece_x = piece_x;
        this.piece_y = piece_y;
        x = piece_x * ratio;
        y = piece_y * ratio;
        this.isInvert = isInvert;
        this.linkedPieces = linkedPieces;
        this.pieceName = pieceName;
        linkedPieces.add(this);
    }

    @Override
    public void move(int pos_X, int pos_Y, int ratio) {
        if (ChessView.getPiece(pos_X * ratio, pos_Y * ratio, 62) != null) {
            if (ChessView.getPiece(pos_X * ratio, pos_Y * ratio, 62).isInvert != isInvert) {
                ChessView.getPiece(pos_X * ratio, pos_Y * ratio, 62).getKill();
            } else {
                x = this.piece_x * ratio;
                y = this.piece_y * ratio;
                return;
            }
        }
        this.piece_x = pos_X;
        this.piece_y = pos_Y;
        x = pos_X * ratio;
        y = pos_Y * ratio;
    }

    @Override
    public void getPieceInfo() {
        System.out.println("Piece x: " + this.x + ",Piece y: " + this.y + ",Piece name:" + this.getPieceName());
    }

    @Override
    public void getKill() {
        // add condition about 
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
