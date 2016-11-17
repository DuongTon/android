package com.theson.gamepacmanproject.manager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {

    public static final Image IMG_PACMAN_START = new ImageIcon(getURL("pacman_0.png")).getImage();
    public static final Image[] IMG_GHOST = new Image[]{
            new ImageIcon(getURL("ghost_1.png")).getImage(),
            new ImageIcon(getURL("ghost_2.png")).getImage(),
    };

    public static final Image[] IMG_PACMAN_LEFT = new Image[]{
            new ImageIcon(getURL("pacman_left_1.png")).getImage(),
            new ImageIcon(getURL("pacman_left_2.png")).getImage(),
            new ImageIcon(getURL("pacman_left_3.png")).getImage(),
    };

    public static final Image[] IMG_PACMAN_TOP = new Image[]{
            new ImageIcon(getURL("pacman_top_1.png")).getImage(),
            new ImageIcon(getURL("pacman_top_2.png")).getImage(),
            new ImageIcon(getURL("pacman_top_3.png")).getImage(),
    };
    public static final Image[] IMG_PACMAN_RIGHT = new Image[]{
            new ImageIcon(getURL("pacman_right_1.png")).getImage(),
            new ImageIcon(getURL("pacman_right_2.png")).getImage(),
            new ImageIcon(getURL("pacman_right_3.png")).getImage(),
    };

    public static final Image[] IMG_PACMAN_BOTTOM = new Image[]{
            new ImageIcon(getURL("pacman_bottom_1.png")).getImage(),
            new ImageIcon(getURL("pacman_bottom_2.png")).getImage(),
            new ImageIcon(getURL("pacman_bottom_3.png")).getImage(),
    };

    public static final Image[] IMG_GHOST_BLINKY = new Image[]{
            new ImageIcon(getURL("blinky_1.png")).getImage(),
            new ImageIcon(getURL("blinky_2.png")).getImage(),
            new ImageIcon(getURL("blinky_3.png")).getImage(),
            new ImageIcon(getURL("blinky_4.png")).getImage(),
            new ImageIcon(getURL("blinky_5.png")).getImage(),
            new ImageIcon(getURL("blinky_6.png")).getImage(),
            new ImageIcon(getURL("blinky_7.png")).getImage(),
            new ImageIcon(getURL("blinky_8.png")).getImage(),
    };


    public static final Image[] IMG_GHOST_CLYDE = new Image[]{
            new ImageIcon(getURL("clyde_1.png")).getImage(),
            new ImageIcon(getURL("clyde_2.png")).getImage(),
            new ImageIcon(getURL("clyde_3.png")).getImage(),
            new ImageIcon(getURL("clyde_4.png")).getImage(),
            new ImageIcon(getURL("clyde_5.png")).getImage(),
            new ImageIcon(getURL("clyde_6.png")).getImage(),
            new ImageIcon(getURL("clyde_7.png")).getImage(),
            new ImageIcon(getURL("clyde_8.png")).getImage(),
    };


    public static final Image[] IMG_GHOST_INKY = new Image[]{
            new ImageIcon(getURL("inky_1.png")).getImage(),
            new ImageIcon(getURL("inky_2.png")).getImage(),
            new ImageIcon(getURL("inky_3.png")).getImage(),
            new ImageIcon(getURL("inky_4.png")).getImage(),
            new ImageIcon(getURL("inky_5.png")).getImage(),
            new ImageIcon(getURL("inky_6.png")).getImage(),
            new ImageIcon(getURL("inky_7.png")).getImage(),
            new ImageIcon(getURL("inky_8.png")).getImage(),
    };


    public static final Image[] IMG_GHOST_PINKY = new Image[]{
            new ImageIcon(getURL("pinky_1.png")).getImage(),
            new ImageIcon(getURL("pinky_2.png")).getImage(),
            new ImageIcon(getURL("pinky_3.png")).getImage(),
            new ImageIcon(getURL("pinky_4.png")).getImage(),
            new ImageIcon(getURL("pinky_5.png")).getImage(),
            new ImageIcon(getURL("pinky_6.png")).getImage(),
            new ImageIcon(getURL("pinky_7.png")).getImage(),
            new ImageIcon(getURL("pinky_8.png")).getImage(),

    };


    public static final Image IMG_ITEM_STONE = new ImageIcon(getURL("stone.png")).getImage();
    public static final Image IMG_ITEM_BEAN_NORMAL = new ImageIcon(getURL("bean_normal_1.png")).getImage();
    public static final Image IMG_ITEM_BEAN_POWER1 = new ImageIcon(getURL("bean_power_1.png")).getImage();
    public static final Image IMG_ITEM_BEAN_POWER2 = new ImageIcon(getURL("bean_power_2.png")).getImage();

    public static final Image IMG_ITEM[] = new Image[]{
            new ImageIcon(getURL("bean_4.png")).getImage(),
            new ImageIcon(getURL("cherry.png")).getImage(),
            new ImageIcon(getURL("strawberry.png")).getImage(),
            new ImageIcon(getURL("pineapple.png")).getImage(),
    };


    public static URL getURL(String fileName) {
        return ImageLoader.class.getResource("/res/images/" + fileName);
    }
}
