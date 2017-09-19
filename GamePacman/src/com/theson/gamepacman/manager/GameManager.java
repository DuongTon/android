package com.theson.gamepacman.manager;


import com.theson.gamepacman.gui.GamePlayPanel;
import com.theson.gamepacman.model.Ghost;
import com.theson.gamepacman.model.Item;
import com.theson.gamepacman.model.Pacman;
import com.theson.gamepacman.sound.PlayerManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    private Pacman pacman;
    private ArrayList<Item> items;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Pacman> pacmans;
    private int pacmanNextOrient;
    private int score;
    private Ghost ghostBinky;
    private Ghost ghostClyde;
    private Ghost ghostInky;
    private Ghost ghostPinky;

    public static boolean IS_GAME_OVER;

    public GameManager() {
        initializePacman();
        initializeGhost();
        items = new ArrayList<>();
        loadMap("map_1.txt");
    }



    public int getPacmanNextOrient() {
        return pacmanNextOrient;
    }

    public void setPacmanNextOrient(int pacmanNextOrient) {
        this.pacmanNextOrient = pacmanNextOrient;
    }

    public int getScore() {
        return score;
    }

    public void initializePacman() {
        pacman = new Pacman(200, 360, pacman.START);
    }

    public void initializeGhost() {
        ghostBinky = new Ghost(200, 200, Ghost.BINKY);
        ghostClyde = new Ghost(180, 240, Ghost.CLYDE);
        ghostInky = new Ghost(200, 240, Ghost.INKY);
        ghostPinky = new Ghost(220, 240, Ghost.PINKY);
        ghostBinky.setGhostNextOrient(ghostBinky.getOrient());
        ghostClyde.setGhostNextOrient(ghostClyde.getOrient());
        ghostInky.setGhostNextOrient(ghostInky.getOrient());
        ghostPinky.setGhostNextOrient(ghostPinky.getOrient());
        ghosts = new ArrayList<>();
        ghosts.add(ghostBinky);
        ghosts.add(ghostClyde);
        ghosts.add(ghostInky);
        ghosts.add(ghostPinky);
    }


    public void loadMap(String fileName) {
        File file = new File(GameManager.class.getResource("/res/maps/" + fileName).getPath());

        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            String line;
            int row = 3;
            while ((line = raf.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    int type = line.charAt(i) - '0';
                    switch (type) {
                        case Item.TYPE_STONE:
                            items.add(new Item(i * Item.SIZE, row * Item.SIZE, Item.TYPE_STONE));
                            break;
                        case Item.TYPE_BEAN_NORMAL:
                            items.add(new Item(i * Item.SIZE, row * Item.SIZE, Item.TYPE_BEAN_NORMAL));
                            break;
                        case Item.TYPE_BEAN_POWER:
                            items.add(new Item(i * Item.SIZE, row * Item.SIZE, Item.TYPE_BEAN_POWER));
                            break;
                    }
                }
                row++;
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPacman(Graphics2D graphics2D) {
        pacman.draw(graphics2D);
    }

    public void drawGhost(Graphics2D graphics2D) {
        ghostBinky.draw(graphics2D);
        ghostClyde.draw(graphics2D);
        ghostInky.draw(graphics2D);
        ghostPinky.draw(graphics2D);
    }

    public void drawItem(Graphics2D graphics2D) {
        assert items != null;
        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(graphics2D);
        }
    }

    public void changePacmanOrient(int orient) {
        pacman.setOrient(orient);
    }


    public void handleMovePacman() {
        for (int i = 0; i < items.size(); i++) {
            boolean isBreakFoeLoop = false;
            if (pacman.collision(items.get(i))) {
                switch (items.get(i).getType()) {
                    case Item.TYPE_STONE:
                        return;
                    case Item.TYPE_BEAN_NORMAL:
                        PlayerManager.getInstance().getsEatBeanNormal().play();
                        items.remove(i);
                        score += 10;
                        isBreakFoeLoop = true;
                        break;
                    case Item.TYPE_BEAN_POWER:
                        PlayerManager.getInstance().getsEatBeanPower().play();
                        items.remove(i);
                        score += 40;
                        Ghost.isDisplay = true;
                        break;
                }
                if (isBreakFoeLoop) {
                    break;
                }
            }
        }
        pacman.move();
    }

    public boolean canChangeOrientPacman() {
        int xx = pacman.getX();
        int yy = pacman.getY();
        switch (pacmanNextOrient) {
            case Pacman.LEFT:
                xx -= 1;
                break;

            case Pacman.TOP:
                yy -= 1;
                break;

            case Pacman.RIGHT:
                xx += 1;
                break;

            case Pacman.BOTTOM:
                yy += 1;
                break;
        }
        Rectangle rPacman = new Rectangle(xx, yy, Pacman.SIZE, Pacman.SIZE);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType() == Item.TYPE_STONE
                    && rPacman.intersects(items.get(i).getrItem())) {
                return false;
            }
        }
        return true;
    }

    public void handleMoveGhosts() {
        moveGhosts(ghostBinky);
        moveGhosts(ghostClyde);
        moveGhosts(ghostInky);
        moveGhosts(ghostPinky);
    }


    public void moveGhosts(Ghost ghost) {
        if (ghost.getX() % Ghost.SIZE == 0 && ghost.getY() % Ghost.SIZE == 0) {
            int i = (new Random()).nextInt(4);
            while (Math.abs(i - ghost.getOrient()) == 2) {
                i = (new Random()).nextInt(4);
            }
            ghost.setGhostNextOrient(i);
            if (ghost.getGhostNextOrient() != ghost.getOrient()) {
                int ori = ghost.getOrient();
                ghost.setOrient(ghost.getGhostNextOrient());
                if (canChangeNextGhostOrient(ghost)) {

                } else {
                    ghost.setOrient(ori);
                }
            }
        }
        for (int i = 0; i < items.size(); i++) {
            boolean isBreakForLoop = false;
            if (ghost.collision(items.get(i))) {
                switch (items.get(i).getType()) {
                    case Item.TYPE_STONE:
                        int ori = ghost.getOrient();
                        ghost.setGhostNextOrient((new Random()).nextInt(4));
                        while (ghost.getGhostNextOrient() == ori) {
                            ghost.setGhostNextOrient((ghost.getGhostNextOrient() + (new Random()).nextInt(3)) % 4);
                        }
                        ghost.setOrient(ghost.getGhostNextOrient());
                        return;
                    case Item.TYPE_BEAN_NORMAL:
                        break;
                    case Item.TYPE_BEAN_POWER:
                        break;
                    default:
                        break;
                }
                if (isBreakForLoop) {
                    break;
                }
            }
        }
        ghost.move();
    }


    private boolean canChangeNextGhostOrient(Ghost ghost) {
        for (int i = 0; i < items.size(); i++) {
            if (ghost.collision(items.get(i))) {
                switch (items.get(i).getType()) {
                    case Item.TYPE_STONE:
                        return false;
                    case Item.TYPE_BEAN_NORMAL:
                        return true;
                    case Item.TYPE_BEAN_POWER:
                        return true;
                    default:
                        return true;
                }
            }
        }
        return true;
    }


    public void collisionPacmanGhost() {
        for (int i = 0; i < ghosts.size(); i++) {
            if (pacman.collisionGhost(ghosts.get(i))) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (ghosts.get(i).getType()) {
                    case Ghost.BINKY:
                        if (Ghost.isDisplay == true) {
                            PlayerManager.getInstance().getsEatGhost().play();
                            ghosts.remove(i);
                            score += 200;
                            Ghost.isDisplay = false;
                            ghostBinky = new Ghost(200, 200, Ghost.PINKY);
                            ghosts.add(ghostBinky);
                            return;
                        } else {
                            PlayerManager.getInstance().getsDie().play();
                            GamePlayPanel.Heart--;
                            initializePacman();
                            initializeGhost();
                            return;
                        }
                    case Ghost.CLYDE:
                        if (Ghost.isDisplay == true) {
                            PlayerManager.getInstance().getsEatGhost().play();
                            ghosts.remove(i);
                            score += 400;
                            Ghost.isDisplay = false;
                            ghostClyde = new Ghost(180, 240, Ghost.CLYDE);
                            ghosts.add(ghostClyde);
                            return;
                        } else {
                            PlayerManager.getInstance().getsDie().play();
                            GamePlayPanel.Heart--;
                            initializePacman();
                            initializeGhost();
                            return;
                        }
                    case Ghost.INKY:
                        if (Ghost.isDisplay == true) {
                            PlayerManager.getInstance().getsEatGhost().play();
                            ghosts.remove(i);
                            score += 800;
                            Ghost.isDisplay = false;
                            ghostInky = new Ghost(200, 240, Ghost.INKY);
                            ghosts.add(ghostInky);
                            return;
                        } else {
                            PlayerManager.getInstance().getsDie().play();
                            GamePlayPanel.Heart--;
                            initializePacman();
                            initializeGhost();
                            return;
                        }
                    case Ghost.PINKY:
                        if (Ghost.isDisplay == true) {
                            PlayerManager.getInstance().getsEatGhost().play();
                            ghosts.remove(i);
                            score += 1600;
                            Ghost.isDisplay = false;
                            ghostPinky = new Ghost(220, 240, Ghost.PINKY);
                            ghosts.add(ghostPinky);
                            return;
                        } else {
                            PlayerManager.getInstance().getsDie().play();
                            GamePlayPanel.Heart--;
                            initializePacman();
                            initializeGhost();
                            return;
                        }
                }
            }
        }
    }

    public boolean isGameOver(){
        if(GamePlayPanel.Heart == 0){
            PlayerManager.getInstance().getsBackGround().stop();
            IS_GAME_OVER = true;
        }
        return IS_GAME_OVER;
    }
}
