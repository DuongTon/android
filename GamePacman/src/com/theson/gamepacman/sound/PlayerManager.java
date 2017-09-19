package com.theson.gamepacman.sound;


import javax.sound.sampled.Clip;

public class PlayerManager {

    private static PlayerManager instance;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }


    private PlayerWav sBackGround;
    private PlayerWav sStartGame;
    private PlayerWav sDie;
    private PlayerWav sEatBeanNormal;
    private PlayerWav sEatBeanPower;
    private PlayerWav sEatGhost;

    private PlayerManager() {

    }

    public PlayerWav getsBackGround() {
        sBackGround = new PlayerWav("sound_BG.wav");
        sBackGround.loop(Clip.LOOP_CONTINUOUSLY);
        return sBackGround;
    }

    public PlayerWav getsStartGame() {
        sStartGame = new PlayerWav("sound_start.wav");
        sStartGame.loop(0);
        return sStartGame;
    }

    public PlayerWav getsEatBeanNormal() {
        sEatBeanNormal = new PlayerWav("sound_eat_bean_normal.wav");
        sEatBeanNormal.loop(0);
        return sEatBeanNormal;
    }

    public PlayerWav getsDie() {
        sDie = new PlayerWav("sound_die.wav");
        sDie.loop(0);
        return sDie;
    }

    public PlayerWav getsEatBeanPower() {
        sEatBeanPower = new PlayerWav("sound_eat_bean_power.wav");
        sEatBeanPower.loop(0);
        return sEatBeanPower;
    }

    public PlayerWav getsEatGhost() {
        sEatGhost = new PlayerWav("sound_eat_ghost.wav");
        return sEatGhost;
    }

}
