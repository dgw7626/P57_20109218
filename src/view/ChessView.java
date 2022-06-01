/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import ai_control.Piece;
import database.chessData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class ChessView extends JFrame implements Observer, MouseListener, MouseMotionListener{

    public static LinkedList<Piece> LinkedPieces = new LinkedList<>();
    public static Piece currentPiece = null;
    //Player panel
    private JPanel loginPanel = new JPanel();
    //private BufferedImage img = ImageIO.read(new File("./resources/T06_bg.jpg"));
    private JLabel logo;
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userName = new JTextField(12);
    private JLabel passLabel = new JLabel("Password:");
    private JTextField userPass = new JTextField(12);
    private Font versionFont = new Font("Courier", Font.BOLD, 12);
    private JLabel version = new JLabel("Chess Game Version 0.2");
    private JButton userLogin = new JButton("Login");
    private boolean isGameActive = false;

    //Option panel
    private JPanel optionPanel = new JPanel();
    private JLabel gameTitle = new JLabel("Welcome To Chess Game");
    private JLabel gameWinner = new JLabel("Player Winner");
    private JButton playButton = new JButton("Player VS AI");
    private JButton showLog = new JButton("Player Log");
    private JButton clearLog = new JButton("Clear Log");
    private JButton loginBack = new JButton("Back");
    private JButton exitGame = new JButton("Exit");
    //In game
    private JButton gameSave = new JButton("Save");
    private JButton gameMenu = new JButton("Game Menu");
    private Tiles tiles;
    private Piece blackPieces[] = new Piece[16];
    private String[] pieceNames = {"rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook"};
    private Piece whitePieces[] = new Piece[16];
    private boolean inGameStatus = false;
    private boolean isAIturn = false;
    public ChessView() {
        this.setTitle("Chess Program 0.2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        // setting X and Y positions
        //setting the logo
        try {
            BufferedImage img = ImageIO.read(new File("./resources/logo.jpg"));
            Image dimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(dimg));
            System.out.println("Image Found: " + img.getData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //loginPanel.setBackground(Color.yellow);
        this.loginPanel.setBackground(Color.WHITE);
        this.loginPanel.setLayout(null);
        this.userLabel.setBounds(20, 20, 120, 10);
        this.userName.setBounds(90, 18, 60, 20);
        this.passLabel.setBounds(20, 40, 120, 10);
        this.userPass.setBounds(90, 38, 60, 20);
        this.userLogin.setBounds(20, 60, 130, 20);
        this.logo.setBounds(180, 10, 80, 80);
        this.version.setBounds(70, 140, 200, 10);
        this.version.setFont(versionFont);
        this.exitGame.setBounds(20, 80, 130, 20);
        this.loginPanel.add(userLabel);
        this.loginPanel.add(userName);
        this.loginPanel.add(passLabel);
        this.loginPanel.add(userPass);
        this.loginPanel.add(userLogin);
        this.loginPanel.add(logo);
        this.loginPanel.add(exitGame);
        this.loginPanel.add(version);
        this.add(loginPanel);
        loginPanel.setSize(300, 200);
        this.setVisible(true);
    }

    public void chessLogin() {
        this.setTitle("Chess Program 0.2");
        this.getContentPane().removeAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        // setting X and Y positions
        //setting the logo
        try {
            BufferedImage img = ImageIO.read(new File("./resources/logo.jpg"));
            Image dimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(dimg));
            System.out.println("Image Found: " + img.getData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //loginPanel.setBackground(Color.yellow);
        this.loginPanel.setBackground(Color.WHITE);
        this.loginPanel.setLayout(null);
        this.userLabel.setBounds(20, 20, 120, 10);
        this.userName.setBounds(90, 18, 60, 20);
        this.passLabel.setBounds(20, 40, 120, 10);
        this.userPass.setBounds(90, 38, 60, 20);
        this.userLogin.setBounds(20, 60, 130, 20);
        this.logo.setBounds(180, 10, 80, 80);
        this.version.setBounds(70, 140, 200, 10);
        this.exitGame.setBounds(20, 80, 130, 20);
        this.version.setFont(versionFont);
        this.loginPanel.add(userLabel);
        this.loginPanel.add(userName);
        this.loginPanel.add(passLabel);
        this.loginPanel.add(userPass);
        this.loginPanel.add(userLogin);
        this.loginPanel.add(logo);
        this.loginPanel.add(version);
        this.loginPanel.add(exitGame);
        this.add(loginPanel);
        this.isGameActive = false;
        loginPanel.setSize(300, 200);
        this.userName.setText("");
        this.userPass.setText("");
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void gameOptions() {
        this.setTitle("Game Options");
        System.out.println("gameOptions");
        this.getContentPane().removeAll();
        this.setSize(400, 400);
        //this.setLayout(null);
        this.optionPanel.setLayout(null);
        this.optionPanel.setBackground(Color.white);
        //adding source code from here
        this.optionPanel.add(gameTitle);
        this.gameTitle.setBounds(90, 20, 200, 20);
        this.optionPanel.add(gameWinner);
        this.gameWinner.setBounds(90, 40, 200, 20);
        this.optionPanel.add(playButton);
        this.playButton.setBounds(90, 60, 200, 20);
        this.optionPanel.add(showLog);
        this.showLog.setBounds(90, 80, 200, 20);
        //  this.optionPanel.add(clearLog);
        // this.clearLog.setBounds(90, 100, 200, 20);

        this.optionPanel.add(loginBack);
        this.loginBack.setBounds(90, 120, 200, 20);

        this.optionPanel.add(exitGame);
        this.exitGame.setBounds(90, 140, 200, 20);

        this.optionPanel.setSize(new Dimension(400, 400));
        this.optionPanel.setVisible(true);

        this.add(optionPanel);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void inGame() {
        try {

            BufferedImage pieces = ImageIO.read(new File("./resources/pieces.png"));
            Image piece_imgs[] = new Image[12];
            int pos = 0;
            for (int y = 0; y < 400; y += 200) {
                for (int x = 0; x < 1200; x += 200) {
                    piece_imgs[pos] = pieces.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                    pos++;
                }
            }

            this.setTitle("Player VS Computer");
            this.getContentPane().removeAll();
            this.setSize(800, 600);
            // adding pieces
            if(!inGameStatus){
            for (int i = 0; i < 8; i++) {
                this.blackPieces[i] = new Piece(i, 0, false, this.pieceNames[i], LinkedPieces, 64);
                this.whitePieces[i] = new Piece(i, 7, true, this.pieceNames[i], LinkedPieces, 64);
            }
            for (int j = 8; j < 16; j++) {
                if (j < 15) {
                    this.blackPieces[j] = new Piece((j - 7), 1, false, "pawn", LinkedPieces, 64);
                    this.whitePieces[j] = new Piece((j - 7), 6, true, "pawn", LinkedPieces, 64);
                } else if (j == 15) {
                    this.blackPieces[j] = new Piece(0, 1, false, "pawn", LinkedPieces, 64);
                    this.whitePieces[j] = new Piece(0, 6, true, "pawn", LinkedPieces, 64);
                }
            }
            inGameStatus = !inGameStatus;
            }
            this.tiles = new Tiles(piece_imgs);
            tiles.setBounds(0, 0, 512, 512);
            this.tiles.addMouseListener(this);
            this.tiles.addMouseMotionListener(this);
            super.add(tiles);
            this.setVisible(true);
            this.revalidate();
            this.repaint();
            System.out.println("Game started");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
        currentPiece = getPiece(e.getX(), e.getY(), 64);
        System.out.println(currentPiece.getPieceName());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentPiece = getPiece(e.getX(), e.getY(), 64);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try{
            if(currentPiece.isIsInvert()){
            // Ai turn boolean
            currentPiece.move(e.getX() / 64, e.getY() /64, 64);
            this.repaint();
            currentPiece = null;
            }
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
        
          }

    @Override
    public void mouseEntered(MouseEvent e) {
          }

    @Override
    public void mouseExited(MouseEvent e) {
        }

    @Override
    public void mouseDragged(MouseEvent e) {
        try{
            if(currentPiece != null && currentPiece.isIsInvert())
            {
            currentPiece.setX(e.getX() - 34);
            currentPiece.setY(e.getY() - 54);
            this.repaint();
            }
            
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
          }

    
    public class Tiles extends JPanel {

        Image[] temps;

        public Tiles(Image[] pieces) {
            this.temps = pieces;
        }

        @Override
        public void paint(Graphics g) {
            boolean isInverted = true;
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    if (isInverted) {
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.white);
                    }
                    g.fillRect(x * 63, y * 63, 63, 63);
                    isInverted = !isInverted;
                }
                isInverted = !isInverted;
            }
            // add pieces here
            for (Piece piece : LinkedPieces) {
                int pos = 0;
                if (piece.getPieceName().equalsIgnoreCase("king")) {
                    pos = 0;
                }
                if (piece.getPieceName().equalsIgnoreCase("queen")) {
                    pos = 1;
                }
                if (piece.getPieceName().equalsIgnoreCase("bishop")) {
                    pos = 2;
                }
                if (piece.getPieceName().equalsIgnoreCase("knight")) {
                    pos = 3;
                }
                if (piece.getPieceName().equalsIgnoreCase("rook")) {
                    pos = 4;
                }
                if (piece.getPieceName().equalsIgnoreCase("pawn")) {
                    pos = 5;
                }
                if (!piece.isIsInvert()) {
                    pos += 6;
                }
                g.drawImage(temps[pos], piece.getX(), piece.getY(), this);

            }
        }
    }

    public void addActionListener(ActionListener listener) {
        this.userLogin.addActionListener(listener);
        // Game panel
        this.loginBack.addActionListener(listener);
        this.showLog.addActionListener(listener);
        this.playButton.addActionListener(listener);

        this.loginBack.addActionListener(listener);
        this.exitGame.addActionListener(listener);
        //  this.clearLog.addActionListener(listener);

        this.playButton.addActionListener(listener);
    }
    // this is where u put optins into this.
    @Override
    public void update(Observable o, Object arg) {
        chessData data = (chessData) arg;
        if (!data.isLoginFlag()) {
            this.getUserName().setText("");
            this.getUserPass().setText("");
            //  JOptionPane.showMessageDialog(this, "data dosent exist", "DATA ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!this.isGameActive) {
            this.gameOptions();
            this.isGameActive = true;
        } else if (data.isQuitFlag()) {

        }
    }

    public JTextField getUserName() {
        return userName;
    }

    public JTextField getUserPass() {
        return userPass;
    }
    public static Piece getPiece(int x, int y,int ratio) {
        int piece_x = x/ratio;
        int piece_y = y/ratio;
        for(Piece piece: LinkedPieces){
            if(piece.getPiece_x() == piece_x && piece.getPiece_y() == piece_y){
                return piece;
            }
        }
        return null;
    }
}
