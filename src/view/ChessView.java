/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import database.chessData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 *
 * @author Admin
 */

public class ChessView extends JFrame implements Observer{
    //Player panel
    private JPanel loginPanel = new JPanel();
    //private BufferedImage img = ImageIO.read(new File("./resources/T06_bg.jpg"));
    private JLabel logo;
    private JLabel userLabel = new JLabel("Username:");
    private JTextField userName = new JTextField(12);
    private JLabel passLabel = new JLabel("Password:");
    private JTextField userPass = new JTextField(12);
    private Font versionFont = new Font("Courier", Font.BOLD,12);
    private JLabel version = new JLabel("Chess Game Version 0.2");
    private JButton userLogin = new JButton("Login");
    private boolean isGameActive = false;
    
    //Option panel
    private JPanel optionPanel = new JPanel();
    private JLabel gameTitle = new JLabel("Welcome To Chess Game");
    private JLabel gameWinner = new JLabel("Player Winner");
    private JButton playButton = new JButton("Player VS AI");
    private JButton showLog = new JButton("Players Log");
    private JButton loginBack = new JButton("Back");
    public ChessView(){
        this.setTitle("Chess Program 0.2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,200);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        // setting X and Y positions
        //setting the logo
        try{
        BufferedImage img = ImageIO.read(new File("./resources/logo.jpg"));
        Image dimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(dimg));
            System.out.println("Image Found: " +img.getData().toString());
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
        //loginPanel.setBackground(Color.yellow);
        this.loginPanel.setBackground(Color.WHITE);
        this.loginPanel.setLayout(null);
        this.userLabel.setBounds(20,20,120,10);
        this.userName.setBounds(90,18,60,20);
        this.passLabel.setBounds(20,40,120,10);
        this.userPass.setBounds(90,38,60,20);
        this.userLogin.setBounds(20,60,130,20);
        this.logo.setBounds(180,10,80,80);
        this.version.setBounds(70,140,200,10);
        this.version.setFont(versionFont);
        this.loginPanel.add(userLabel);
        this.loginPanel.add(userName);
        this.loginPanel.add(passLabel);
        this.loginPanel.add(userPass);
        this.loginPanel.add(userLogin);
        this.loginPanel.add(logo);
        this.loginPanel.add(version);
        this.add(loginPanel);
        loginPanel.setSize(300, 200);
        this.setVisible(true);
    }
    
    public void gameOptions(){
        this.setTitle("Game Options");
        System.out.println("gameOptions");
        this.getContentPane().removeAll();
        this.setSize(400,400);
        this.optionPanel.setBackground(Color.red);
        this.optionPanel.setSize(new Dimension(400,400));
        this.optionPanel.setVisible(true);
        
        this.add(optionPanel);
        this.revalidate();
        this.repaint();
    }
    
    public void addActionListener(ActionListener listener){
        this.userLogin.addActionListener(listener);
    }
    // this is where u put optins into this.
    @Override
    public void update(Observable o, Object arg){
        chessData data = (chessData) arg;
        if(!data.isLoginFlag()){
            this.getUserName().setText("");
            this.getUserPass().setText("");
            JOptionPane.showMessageDialog(this, "data dosent exist", "DATA ERROR",JOptionPane.ERROR_MESSAGE);
        } 
        else if(!this.isGameActive){
            this.gameOptions();
            this.isGameActive = true;
        }
        else if(data.isQuitFlag()){
            
        }
    }

    public JTextField getUserName() {
        return userName;
    }

    public JTextField getUserPass() {
        return userPass;
    }

}
