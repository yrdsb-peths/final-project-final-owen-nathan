import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Currency here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Currency {
    public static int coins = 0;

    public static void addCoins(int amount) {
        coins += amount;
    }

    public static void subtractCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
        }
    }

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int amount) {
        coins = amount;
    }
}

