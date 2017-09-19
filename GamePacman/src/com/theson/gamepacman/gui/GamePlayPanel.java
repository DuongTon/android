package com.theson.gamepacman.gui;



import com.theson.gamepacman.manager.GameManager;
import com.theson.gamepacman.manager.ImageLoader;
import com.theson.gamepacman.model.Item;
import com.theson.gamepacman.model.Pacman;
import com.theson.gamepacman.sound.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.BitSet;

public class GamePlayPanel extends BaseContainer implements Runnable {

    public static final int WIDTH_PANEL = 20 * 21 + 6;
    public static final int HEIGHT_PANEL = 20 * 21 + 29;

    public static int Heart;
    private GameManager gameManager;
    private boolean isRunning;
    private KeyAdapter keyAdapter;
    private JLabel lbGameOver;
    private BitSet bitSet;

    public GamePlayPanel() {
        super();
    }

    @Override
    protected void initializeContainer() {
        setLayout(null);
        setBounds(0, 60, WIDTH_PANEL, HEIGHT_PANEL);
        setBackground(Color.BLACK);
    }


    @Override
    protected void initializeComponents() {
        PlayerManager.getInstance().getsBackGround().play();
        PlayerManager.getInstance().getsDie().stop();
        initializeListener();
        isRunning = false;
        gameManager = new GameManager();
        startGame();
        Heart = 3;
    }

    private void initializeListener() {
        bitSet = new BitSet(256);
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bitSet.set(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bitSet.clear(e.getKeyCode());
            }
        };
        addKeyListener(keyAdapter);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        gameManager.drawItem(graphics2D);
        gameManager.drawPacman(graphics2D);
        gameManager.drawGhost(graphics2D);


        drawScore(graphics2D);
        drawLiveHeart(graphics2D);
        drawItem(graphics2D);
        drawGameOver(graphics2D);

    }


    public void drawScore(Graphics2D g) {
        g.setFont(new Font("Tahoma", Font.PLAIN, 25));

        g.setColor(Color.RED);
        g.drawString("HI SCORE", 20, 40);
        g.setColor(Color.WHITE);
        g.drawString(gameManager.getScore() + " ", 160, 40);

        g.setColor(Color.WHITE);
        g.drawString("SCORE", 250, 40);
        g.setColor(Color.YELLOW);
        g.drawString(gameManager.getScore() + " ", 350, 40);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawLine(201, 230, 218, 230);
    }

    public void drawLiveHeart(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString("LIVES", 20, HEIGHT_PANEL + 70);
        ArrayList<Pacman> pacmans;
        pacmans = new ArrayList<>();
        pacmans.add(new Pacman(100, HEIGHT_PANEL + 50, Pacman.LEFT));
        pacmans.add(new Pacman(120, HEIGHT_PANEL + 50, Pacman.LEFT));
        pacmans.add(new Pacman(140, HEIGHT_PANEL + 50, Pacman.LEFT));
        for (int i = Heart - 1; i >= 0; i--) {
          pacmans.get(i).draw(g);
        }
    }

    public void drawItem(Graphics2D graphics2D){
        for(int i = 0; i< ImageLoader.IMG_ITEM.length; i++){
            graphics2D.drawImage(ImageLoader.IMG_ITEM[i], 320+20*i, HEIGHT_PANEL + 50, Item.SIZE, Item.SIZE, null);
        }

    }

    public void drawGameOver(Graphics2D graphics2D) {
        lbGameOver = new JLabel("Game Over");
        Font font = new Font("Tahoma", Font.PLAIN, 40);
        FontMetrics fontMetrics = getFontMetrics(font);
        lbGameOver.setFont(font);
        lbGameOver.setForeground(Color.WHITE);
        int wLBGO = fontMetrics.stringWidth(lbGameOver.getText());
        int hLBGO = fontMetrics.getHeight();
        lbGameOver.setBounds((GUI.WIDTH_FRAME - wLBGO) / 2, (GUI.HEIGHT_FRAME - hLBGO) / 2, wLBGO, hLBGO);
        lbGameOver.setVisible(false);
        add(lbGameOver);

    }


    public void startGame() {
        Thread thread = new Thread(this);
        thread.start();
        isRunning = true;
    }


    public void handlePacman() {
        if (bitSet.get(KeyEvent.VK_LEFT)) {
            gameManager.setPacmanNextOrient(Pacman.LEFT);
        }
        if (bitSet.get(KeyEvent.VK_UP)) {
            gameManager.setPacmanNextOrient(Pacman.TOP);
        }
        if (bitSet.get(KeyEvent.VK_RIGHT)) {
            gameManager.setPacmanNextOrient(Pacman.RIGHT);
        }
        if (bitSet.get(KeyEvent.VK_DOWN)) {
            gameManager.setPacmanNextOrient(Pacman.BOTTOM);
        }
        gameManager.handleMovePacman();
    }


    @Override
    public void run() {
        while (isRunning) {
            if (gameManager.canChangeOrientPacman()) {
                gameManager.changePacmanOrient(gameManager.getPacmanNextOrient());
            }
            if (gameManager.isGameOver()) {
                lbGameOver.setVisible(true);
                repaint();
                isRunning = false;
            }
            gameManager.collisionPacmanGhost();
            handlePacman();
            gameManager.handleMoveGhosts();
            repaint();
            requestFocus();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
