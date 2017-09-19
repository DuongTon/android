package com.theson.gamepacman.gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public static final int WIDTH_FRAME = 20 * 21 + 6;
    public static final int HEIGHT_FRAME = 20 * 21 + 150;

    public GUI() {
        initializeGUI();
        initializeContainer();
    }

    private void initializeGUI() {
        setTitle("Game Pacman");
        setLayout(new CardLayout());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeContainer() {
        add(new MyContainer());
    }
}
