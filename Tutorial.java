import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial extends World
{
    public boolean landed = false;
    public boolean crossed = false;
    Label text = new Label("a gorilla finds himself stranded in a new world...", 20);
    Label wasdLabel = new Label("WASD to move", 50); // ← track label here

    public Tutorial()
    {
        super(600, 400, 1);
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);

        addObject(text, 180, 20);

        Ship asteroid = new Ship();
        addObject(asteroid, Ship.x, 0);

        // Add WASD label now; we’ll remove it later
        addObject(wasdLabel, 300, 300);
    }

    public void act() {
        if (crossed && wasdLabel != null) {
            removeObject(wasdLabel);
            wasdLabel = null;
        }
    }
}

