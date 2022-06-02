/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import ai_control.Piece;
import database.chessData;
import ai_control.ChessAI;
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
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Hanul Rheem 20109218
 */
public class ChessView extends JFrame implements Observer, MouseListener, MouseMotionListener {

    public static ArrayList<Piece> LinkedPieces = new ArrayList<>();
    public static Piece currentPiece = null;

    //Player panel
    private JPanel loginPanel = new JPanel();
    private JLabel logo;
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userName = new JTextField(12);
    private JLabel passLabel = new JLabel("Password:");
    private JTextField userPass = new JTextField(12);
    private Font versionFont = new Font("Courier", Font.BOLD, 12);
    private JLabel version = new JLabel("Chess Game Version 0.4.1");
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
    public boolean isAIturn = false;
    public ChessAI ai = new ChessAI();
    private int counter = 0;
    public int playerScore;
    
    // view constructor
    public ChessView() {
        this.setTitle("Chess Program 0.4.1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        try {
            BufferedImage img = ImageIO.read(new File("./resources/logo.jpg"));
            Image dimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(dimg));
            System.out.println("Image Found: " + img.getData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // welcome screen panel
    public void chessLogin() {
        this.setTitle("Chess Program 0.4.1");
        this.getContentPane().removeAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        try {
            BufferedImage img = ImageIO.read(new File("./resources/logo.jpg"));
            Image dimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(dimg));
            System.out.println("Image Found: " + img.getData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // game options panel
    public void gameOptions() {
        this.setTitle("Game Options");
        System.out.println("gameOptions");
        this.getContentPane().removeAll();
        this.setSize(400, 400);
        this.optionPanel.setLayout(null);
        this.optionPanel.setBackground(Color.white);
        this.optionPanel.add(gameTitle);
        this.gameTitle.setBounds(90, 20, 200, 20);
        this.optionPanel.add(gameWinner);
        this.gameWinner.setBounds(90, 40, 200, 20);
        this.optionPanel.add(playButton);
        this.playButton.setBounds(90, 60, 200, 20);
        this.optionPanel.add(showLog);
        this.showLog.setBounds(90, 80, 200, 20);
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

    // in active game panel
    public void inGame() {
        try {
            this.getContentPane().removeAll();
            ai.InitializeBehaviourSets();
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
            this.setSize(800, 545);
            if (!inGameStatus) {
                for (int i = 0; i < 8; i++) {
                    this.blackPieces[i] = new Piece(i, 0, false, this.pieceNames[i], LinkedPieces, 63);
                    this.whitePieces[i] = new Piece(i, 7, true, this.pieceNames[i], LinkedPieces, 63);
                }
                for (int j = 8; j < 16; j++) {
                    if (j < 15) {
                        this.blackPieces[j] = new Piece((j - 7), 1, false, "pawn", LinkedPieces, 63);
                        this.whitePieces[j] = new Piece((j - 7), 6, true, "pawn", LinkedPieces, 63);
                    } else if (j == 15) {
                        this.blackPieces[j] = new Piece(0, 1, false, "pawn", LinkedPieces, 63);
                        this.whitePieces[j] = new Piece(0, 6, true, "pawn", LinkedPieces, 63);
                    }
                }
                inGameStatus = !inGameStatus;
            }
            this.tiles = new Tiles(piece_imgs);
            tiles.setBounds(0, 0, 512, 512);
            this.tiles.addMouseListener(this);
            this.tiles.addMouseMotionListener(this);
            super.add(tiles);
            this.add(exitGame);
            this.add(gameSave);
            this.add(gameMenu);
            this.exitGame.setBounds(540, 200, 200, 20);
            this.gameSave.setBounds(540, 180, 200, 20);
            this.gameMenu.setBounds(540, 160, 200, 20);
            this.setVisible(true);
            super.revalidate();
            super.repaint();
            System.out.println("Game started");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            System.out.println("mouse clicked");
            currentPiece = getPiece(e.getX(), e.getY(), 62);
            System.out.println(currentPiece.getPieceName());
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            currentPiece = getPiece(e.getX(), e.getY(), 62);
            System.out.println("X:" + e.getX() + ",Y:" + e.getY());
            currentPiece.getPieceInfo();
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            if (currentPiece.isIsInvert() && !this.isAIturn) {
                this.playerScore = 0;
                if (counter == 7) {
                    Random random = new Random();
                    this.playerScore = random.nextInt(100);

                    JOptionPane.showMessageDialog(this, "Thanks for playing demo!", "DEMO TIME IS OVER", JOptionPane.PLAIN_MESSAGE);
                }
                currentPiece.move(e.getX() / 64, e.getY() / 64, 63);
                this.repaint();
                this.isAIturn = true;
                if (this.isAIturn == true) {
                    currentPiece = getPiece(ai.ChessAIPath.get(counter)[0], ai.ChessAIPath.get(counter)[1], 64);
                    currentPiece.getPieceInfo();
                    System.out.println("X:" + e.getX() + ",Y:" + e.getY());
                    currentPiece.move(ai.ChessAIPath.get(counter)[2] / 64, ai.ChessAIPath.get(counter)[3] / 64, 63);
                    this.isAIturn = false;
                    counter++;
                    this.repaint();
                    currentPiece = null;
                }
            }

        } catch (NullPointerException ex) {
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
        try {
            if (currentPiece != null && currentPiece.isIsInvert() && !this.isAIturn) {
                currentPiece.setX(e.getX() - 34);
                currentPiece.setY(e.getY() - 54);
                this.repaint();
            }

        } catch (NullPointerException ex) {
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
            String[] ChessPieces = {"king","queen","bishop","knight","rock","pawn"};
            for(int i = 0; i < LinkedPieces.size(); i++){
                int pos = 0;
                if(LinkedPieces.get(i).getPieceName().equalsIgnoreCase(ChessPieces[0])) pos = 0;
                if(LinkedPieces.get(i).getPieceName().equalsIgnoreCase(ChessPieces[1])) pos = 1;
                if(LinkedPieces.get(i).getPieceName().equalsIgnoreCase(ChessPieces[2])) pos = 2;
                if(LinkedPieces.get(i).getPieceName().equalsIgnoreCase(ChessPieces[3])) pos = 3;
                if(LinkedPieces.get(i).getPieceName().equalsIgnoreCase(ChessPieces[4])) pos = 4;
                if(LinkedPieces.get(i).getPieceName().equalsIgnoreCase(ChessPieces[5])) pos = 5;
                if(!LinkedPieces.get(i).isIsInvert()) pos += 6;
                g.drawImage(temps[pos], LinkedPieces.get(i).getX(), LinkedPieces.get(i).getY(), this);
            }
        }
    }

    public void addActionListener(ActionListener listener) {
        this.userLogin.addActionListener(listener);
        this.loginBack.addActionListener(listener);
        this.showLog.addActionListener(listener);
        this.playButton.addActionListener(listener);
        this.loginBack.addActionListener(listener);
        this.exitGame.addActionListener(listener);
        this.playButton.addActionListener(listener);
        this.gameMenu.addActionListener(listener);
        this.gameSave.addActionListener(listener);
    }

    // updating input values
    @Override
    public void update(Observable o, Object arg) {
        chessData data = (chessData) arg;
        if (!data.isLoginFlag()) {
            this.getUserName().setText("");
            this.getUserPass().setText("");
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

    public static Piece getPiece(int x, int y, int ratio) {
        try {
            if (x != 0 && y != 0 && ratio != 0) {
                int piece_x = x / ratio;
                int piece_y = y / ratio;
                if (LinkedPieces != null) {
                    for (Piece piece : LinkedPieces) {
                        if (piece.getPiece_x() == piece_x) {
                            if (piece.getPiece_y() == piece_y) {
                                return piece;
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("incorrect input and out of field");
        }
        return null;
    }

}
