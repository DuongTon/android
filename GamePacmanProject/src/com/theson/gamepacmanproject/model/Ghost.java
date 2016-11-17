package com.theson.gamepacmanproject.model;

import com.theson.gamepacmanproject.gui.GamePlayPanel;
import com.theson.gamepacmanproject.manager.ImageLoader;

import java.awt.*;
import java.util.Date;
import java.util.Random;

public class Ghost extends GameObj {


    public static int SIZE = 20;

    public static final int BINKY = 0;
    public static final int CLYDE = 1;
    public static final int INKY = 2;
    public static final int PINKY = 3;


    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 0;

    private int isIndex;
    private int index;
    public static boolean isDisplay = false;
    private int type;
    private int orient;
    private int ghostNextOrient;
    private int count;
    private static Random random = new Random();

    private Rectangle rGhost;

    public Ghost(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        orient = random.nextInt(4);
        rGhost = new Rectangle(this.x, this.y, SIZE, SIZE);
        index = 0;
        isIndex = 0;
    }

    public int getGhostNextOrient() {
        return ghostNextOrient;
    }

    public void setGhostNextOrient(int ghostNextOrient) {
        this.ghostNextOrient = ghostNextOrient;
    }


    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getType() {
        return type;
    }

    public int getOrient() {
        return orient;
    }

    public Rectangle getrGhost() {
        return rGhost;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (!isDisplay) {
            switch (type) {
                case BINKY:
                    graphics2D.drawImage(ImageLoader.IMG_GHOST_BLINKY[index], x, y, SIZE, SIZE, null);
                    break;
                case CLYDE:
                    graphics2D.drawImage(ImageLoader.IMG_GHOST_CLYDE[index], x, y, SIZE, SIZE, null);
                    break;
                case INKY:
                    graphics2D.drawImage(ImageLoader.IMG_GHOST_INKY[index], x, y, SIZE, SIZE, null);
                    break;
                case PINKY:
                    graphics2D.drawImage(ImageLoader.IMG_GHOST_PINKY[index], x, y, SIZE, SIZE, null);
                    break;
            }
        } else {
            if (count == 0) {
                graphics2D.drawImage(ImageLoader.IMG_GHOST[0], x, y, SIZE, SIZE, null);
            } else {
                graphics2D.drawImage(ImageLoader.IMG_GHOST[1], x, y, SIZE, SIZE, null);
            }

        }
        isIndex++;
        if (isIndex == 10) {
            index++;
            if (index == 7) {
                index = 0;
            }
            isIndex = 0;
        }

        if (count > 2) {
            count = 0;
        } else {
            count++;
        }

    }


    public void move() {
        switch (orient) {
            case LEFT:
                x--;
                if (x < -SIZE) {
                    x = GamePlayPanel.WIDTH_PANEL;
                }
                break;
            case TOP:
                y--;
                if (y < -SIZE) {
                    y = GamePlayPanel.HEIGHT_PANEL;
                }
                break;
            case RIGHT:
                x++;
                if (x > GamePlayPanel.WIDTH_PANEL) {
                    x = -SIZE;
                }
                break;
            case BOTTOM:
                y++;
                if (y > GamePlayPanel.HEIGHT_PANEL) {
                    y = -SIZE;
                }
                break;

            default:
        }
    }

    public boolean collision(Item item) {
        int xx = x;
        int yy = y;
        switch (orient) {
            case LEFT:
                xx -= 1;
                break;
            case TOP:
                yy -= 1;
                break;
            case RIGHT:
                xx += 1;
                break;
            case BOTTOM:
                yy += 1;
                break;
            default:
        }

        rGhost = new Rectangle(xx, yy, SIZE, SIZE);
        switch (item.getType()) {
            case Item.TYPE_STONE:
                return rGhost.intersects(item.getrItem());
            case Item.TYPE_BEAN_NORMAL:
                return false;
            case Item.TYPE_BEAN_POWER:
                return false;
            default:
                return false;
        }
    }
}