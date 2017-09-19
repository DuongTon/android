package com.theson.gamepacman.gui;




import com.theson.gamepacman.interfaces.OnChangePanelListener;
import com.theson.gamepacman.manager.GameManager;
import com.theson.gamepacman.manager.ImageLoader;
import com.theson.gamepacman.model.Ghost;
import com.theson.gamepacman.model.Item;
import com.theson.gamepacman.model.Pacman;
import com.theson.gamepacman.sound.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuGamePanel extends BaseContainer implements ActionListener{

    private OnChangePanelListener listener;
    private JButton btnPlayGame;
    private JButton btnExit;
    private JButton btnAbout;
    private GameManager gameManager;
    private MouseAdapter mouseAdapter;


    public void setOnChangePanelListener(OnChangePanelListener listener) {
        this.listener = listener;
    }

    public MenuGamePanel(){
        PlayerManager.getInstance().getsStartGame().play();
        PlayerManager.getInstance().getsBackGround().stop();
    }

    @Override
    protected void initializeContainer() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    protected void initializeComponents() {
        gameManager = new GameManager();
        drawPlayGame();
        drawExit();
        drawAbout();
        initializeMouseListener();

    }


    public void drawPlayGame(){
        btnPlayGame = new JButton("PLAY GAME");
        Font font = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        btnPlayGame.setForeground(Color.WHITE);
        btnPlayGame.setFont(font);
        btnPlayGame.setBackground(Color.BLACK);
        btnPlayGame.setBorder(null);
        int wLBPlayGame = fontMetrics.stringWidth("PLAY GAME");
        int hLBPlayGame = fontMetrics.getHeight();
        btnPlayGame.setSize(wLBPlayGame, hLBPlayGame);
        btnPlayGame.setLocation((GUI.WIDTH_FRAME - wLBPlayGame) / 2, GUI.WIDTH_FRAME / 2 + 30);
        add(btnPlayGame);
        btnPlayGame.setActionCommand("PLAYGAME");
        btnPlayGame.addActionListener(this);
    }

    public void drawExit(){
        btnExit = new JButton("EXIT");
        Font font = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(font);
        btnExit.setBackground(Color.BLACK);
        btnExit.setBorder(null);
        int wLBExitPanel = fontMetrics.stringWidth("EXIT");
        int hLBExit = fontMetrics.getHeight();
        btnExit.setSize(wLBExitPanel, hLBExit);
        btnExit.setLocation((GUI.WIDTH_FRAME - wLBExitPanel) / 2, GUI.WIDTH_FRAME / 2 + 70);
        add(btnExit);
        btnExit.setActionCommand("EXIT");
        btnExit.addActionListener(this);
    }

    public void drawAbout(){
        btnAbout = new JButton("ABOUT");
        Font font = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        btnAbout.setForeground(Color.WHITE);
        btnAbout.setFont(font);
        btnAbout.setBackground(Color.BLACK);
        btnAbout.setBorder(null);
        int wLBAbout = fontMetrics.stringWidth("ABOUT");
        int hLBAbout = fontMetrics.getHeight();
        btnAbout.setSize(wLBAbout, hLBAbout);
        btnAbout.setLocation((GUI.WIDTH_FRAME - wLBAbout) / 2, GUI.WIDTH_FRAME / 2 + 110);
        add(btnAbout);
        btnAbout.setActionCommand("ABOUT");
        btnAbout.addActionListener(this);
    }


    public void initializeMouseListener(){

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(e.getComponent().equals(btnPlayGame)){
                    btnPlayGame.setForeground(Color.YELLOW);
                }if(e.getComponent().equals(btnAbout)){
                    btnAbout.setForeground(Color.BLUE);
                }if(e.getComponent().equals(btnExit)){
                    btnExit.setForeground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(e.getComponent().equals(btnPlayGame)){
                    btnPlayGame.setForeground(Color.WHITE);
                } if(e.getComponent().equals(btnAbout)){
                    btnAbout.setForeground(Color.WHITE);
                }if(e.getComponent().equals(btnExit)){
                    btnExit.setForeground(Color.WHITE);
                }
            }
        };
        btnPlayGame.addMouseListener(mouseAdapter);
        btnAbout.addMouseListener(mouseAdapter);
        btnExit.addMouseListener(mouseAdapter);


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        String s = "PA  MAN";
        Font small = new Font("Helvetica", Font.BOLD, 60);
        FontMetrics fontMetrics = this.getFontMetrics(small);

        graphics2D.setColor((Color.WHITE));
        graphics2D.setFont(small);
        graphics2D.drawString(s, (GUI.WIDTH_FRAME  - fontMetrics.stringWidth(s)) / 2,100);
        graphics2D.drawImage(ImageLoader.IMG_PACMAN_RIGHT[0],163,55,50,50,null);

        graphics2D.drawImage(ImageLoader.IMG_GHOST_BLINKY[0],190,180, Ghost.SIZE,Ghost.SIZE,null);
        graphics2D.drawImage(ImageLoader.IMG_GHOST_CLYDE[0],210,180,Ghost.SIZE,Ghost.SIZE,null);
        graphics2D.drawImage(ImageLoader.IMG_GHOST_INKY[0],230,180,Ghost.SIZE,Ghost.SIZE,null);
        graphics2D.drawImage(ImageLoader.IMG_GHOST_PINKY[0],250,180,Ghost.SIZE,Ghost.SIZE,null);
        graphics2D.drawImage(ImageLoader.IMG_ITEM_BEAN_POWER2,170,180, Item.SIZE,Item.SIZE,null);
        graphics2D.drawImage(ImageLoader.IMG_PACMAN_RIGHT[0],150,180, Pacman.SIZE,Pacman.SIZE,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "PLAYGAME":
                listener.change();
                break;
            case "EXIT":
                System.exit(0);
                break;
            case "ABOUT":
                JOptionPane.showMessageDialog(null,"Game Pacman by TS");
            break;

        }
    }
}















