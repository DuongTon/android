package com.theson.gamepacman.gui;


import com.theson.gamepacman.interfaces.OnChangePanelListener;

import java.awt.*;

public class MyContainer extends BaseContainer implements OnChangePanelListener {

    public static MyContainer instance;
    private MenuGamePanel menuGame;
    private GamePlayPanel gamePlayPanel;

    @Override
    protected void initializeContainer() {
        setLayout(new CardLayout());
        setBackground(Color.BLACK);
    }

    @Override
    protected void initializeComponents() {
        menuGame = new MenuGamePanel();
        menuGame.setOnChangePanelListener(this);
        add(menuGame);
    }
    @Override
    public void change() {
        remove(menuGame);
        if(gamePlayPanel == null){
            gamePlayPanel = new GamePlayPanel();
        }
        add(gamePlayPanel);
        revalidate();
        repaint();
    }
}
