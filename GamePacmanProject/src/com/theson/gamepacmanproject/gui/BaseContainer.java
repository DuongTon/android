package com.theson.gamepacmanproject.gui;


import javax.swing.*;

public abstract class BaseContainer extends JPanel{

    public BaseContainer(){
        initializeContainer();
        initializeComponents();
    }

    protected abstract void initializeContainer();

    protected abstract void initializeComponents();

}
