package com.theson.gamepacmanproject.model;


import com.theson.gamepacmanproject.manager.ImageLoader;

import java.awt.*;

public class Item extends GameObj {

    public static final int SIZE = 20;
    public static final int TYPE_STONE = 1;
    public static final int TYPE_BEAN_NORMAL = 2;
    public static final int TYPE_BEAN_POWER = 3;

    private int type;
    private Rectangle rItem;

    private  int animestate;
    public Item(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        rItem = new Rectangle(x, y, SIZE, SIZE);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Rectangle getrItem() {
        return rItem;
    }

    public void advance() {
        if (animestate > 12) {
            animestate = 0;
        } else {
            animestate++;
        }
    }
    @Override
    public void draw(Graphics2D graphics2D) {
        advance();
        switch (type) {
            case 1:
                graphics2D.drawImage(ImageLoader.IMG_ITEM_STONE, x, y, SIZE, SIZE, null);
                break;

            case 2:
                graphics2D.drawImage(ImageLoader.IMG_ITEM_BEAN_NORMAL, x, y, SIZE, SIZE, null);
                break;

            case 3:
                if(animestate > 1){
                    graphics2D.drawImage(ImageLoader.IMG_ITEM_BEAN_POWER1, x, y, SIZE, SIZE, null);
                }else if(animestate>12){
                    graphics2D.drawImage(ImageLoader.IMG_ITEM_BEAN_POWER2, x, y, SIZE, SIZE, null);
                }
                break;
        }
    }


}
