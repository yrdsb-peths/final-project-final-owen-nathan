import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(600, 400, 1);

        // Set background image
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        // Add asteroid at saved x position
        Asteroid asteroid = new Asteroid();
        addObject(asteroid, Asteroid.x, 0);

        // Add gorilla
        Gorilla gorilla = new Gorilla();
        addObject(gorilla, 300, 300);
    }
}
