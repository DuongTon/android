package com.theson.gamepacman.model;

import com.theson.gamepacman.gui.GamePlayPanel;
import com.theson.gamepacman.manager.ImageLoader;

import java.awt.*;

public class Pacman extends GameObj {

    public static final int SIZE = 20;

    public static final int START = 0;

    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;

    private int orient;

    private Rectangle rPacman;
    private int animestate = 0;

    public Pacman(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        rPacman = new Rectangle(x, y, SIZE, SIZE);
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
           switch (orient) {
               case START:
                   graphics2D.drawImage(ImageLoader.IMG_PACMAN_START, x, y, SIZE, SIZE, null);
                   break;
               case LEFT:
                   if (animestate < 8) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_LEFT[0], x, y, SIZE, SIZE, null);
                   } else if (animestate < 16) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_LEFT[1], x, y, SIZE, SIZE, null);
                   } else if (animestate < 24) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_LEFT[2], x, y, SIZE, SIZE, null);
                   }
                   break;
               case TOP:
                   if (animestate < 8) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_TOP[0], x, y, SIZE, SIZE, null);
                   } else if (animestate < 16) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_TOP[1], x, y, SIZE, SIZE, null);
                   } else if (animestate < 24) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_TOP[2], x, y, SIZE, SIZE, null);
                   }
                   break;
               case RIGHT:
                   if (animestate < 8) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_RIGHT[0], x, y, SIZE, SIZE, null);
                   } else if (animestate < 16) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_RIGHT[1], x, y, SIZE, SIZE, null);
                   } else if (animestate < 24) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_RIGHT[2], x, y, SIZE, SIZE, null);
                   }
                   break;
               case BOTTOM:
                   if (animestate < 8) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_BOTTOM[0], x, y, SIZE, SIZE, null);
                   } else if (animestate < 16) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_BOTTOM[1], x, y, SIZE, SIZE, null);
                   } else if (animestate < 24) {
                       graphics2D.drawImage(ImageLoader.IMG_PACMAN_BOTTOM[2], x, y, SIZE, SIZE, null);
                   }
                   break;
               default:
           }
        if (animestate > 20) {
            animestate = 0;
        } else {
            animestate++;
        }
    }

    public void move() {

        switch (orient) {
            case LEFT:
                x--;
                if (x < -SIZE) {
                    return;
                }
                break;
            case TOP:
                y--;
                if (y < -SIZE) {
                    return;
                }
                break;
            case RIGHT:
                x++;
                if (x > GamePlayPanel.WIDTH_PANEL) {
                    return;
                }
                break;
            case BOTTOM:
                y++;
                if (y > GamePlayPanel.HEIGHT_PANEL) {
                    return;
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


        Rectangle rPacman = new Rectangle(xx, yy, SIZE, SIZE);
        Rectangle r = rPacman.intersection(item.getrItem());
        switch (item.getType()) {
            case Item.TYPE_STONE:
                return rPacman.intersects(item.getrItem());
            case Item.TYPE_BEAN_NORMAL:
                return Item.SIZE / 4 * 3 < r.getWidth()
                        && Item.SIZE / 4 * 3 < r.getHeight();
            case Item.TYPE_BEAN_POWER:

                return Item.SIZE / 4 * 3 < r.getWidth()
                        && Item.SIZE / 4 * 3 < r.getHeight();
            default:
                return false;
        }
    }


    public boolean collisionGhost(Ghost ghosts) {
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
        Rectangle rPacman = new Rectangle(xx, yy, SIZE, SIZE);
        Rectangle r = rPacman.intersection(ghosts.getrGhost());
        switch (ghosts.getType()) {
            case Ghost.BINKY:
                return Ghost.SIZE / 2 < r.getWidth()
                        && Ghost.SIZE / 2 < r.getHeight();
            case Ghost.CLYDE:
                return Ghost.SIZE / 2 < r.getWidth()
                        && Ghost.SIZE / 2 < r.getHeight();
            case Ghost.INKY:
                return Ghost.SIZE / 2 < r.getWidth()
                        && Ghost.SIZE / 2 < r.getHeight();
            case Ghost.PINKY:
                return Ghost.SIZE / 2 < r.getWidth()
                        && Ghost.SIZE / 2 < r.getHeight();
            default:
                return false;
        }
    }

}
