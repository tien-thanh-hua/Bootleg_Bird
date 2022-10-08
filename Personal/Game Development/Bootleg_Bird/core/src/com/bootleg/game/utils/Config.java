package com.bootleg.game.utils;

import java.util.Random;

public class Config {
    public static String soundJump = "jump";
    public static String soundScore = "score";
    public static String soundHit = "hit";

    public static int landHeight = 112;
    public static int landWidth = 336;

    public static float moveLeftDuration = 3f;

    public static int jumpHeight = 60;
    public static float jumpDuration = 0.2f;

    // time between each added pipes
    public static float pipeAddTime = 2;
    // vertical distance between pipes. The larger it is, the easier to play
    public static float pipeGap = 120;

    public static int random(int min, int max) {
        Random ran = new Random();
        return ran.nextInt(max - min + 1) + min;
    }
}
