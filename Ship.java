import greenfoot.*;

public class Ship extends Actor {
    public static int x = 0;
    GreenfootSound fallingSound = new GreenfootSound("bomb-dropping-101151.mp3");
    private static boolean played = false;
    
    public void act() {
        setLocation(getX(), getY() + 2);
        if(played == false) {
            fallingSound.play();
            played = true;
        }
        
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            move(-2);
        } else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            move(2);
        }

        if(getWorld() instanceof Cutscene1 && getY() >= 399) {
            x = getX(); 
            Greenfoot.setWorld(new Tutorial());
            return;
        }

        if(getWorld() instanceof Tutorial && getY() >= 200) {
            Tutorial world = (Tutorial) getWorld();
            world.landed = true;

            int asteroidX = getX(); 

            Boom boom = new Boom(asteroidX, 200);
            world.addObject(boom, asteroidX, 200);

            world.removeObject(this); 
            return;
        }
    }

    public Ship() {
        GreenfootImage asteroid = new GreenfootImage("images/ship.png");
        asteroid.scale(60, 60);
        setImage(asteroid);
    }
}
