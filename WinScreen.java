import greenfoot.*;

public class WinScreen extends World {
    GreenfootSound fallingSound = new GreenfootSound("level-win-6416.mp3");
    public static boolean played2 = false;
    public WinScreen() {
        super(600, 400, 1);
        GreenfootImage bg = getBackground();
        bg.setColor(Color.BLACK);
        bg.fill();

        GreenfootImage text1 = new GreenfootImage("One Gorilla Is Stronger Than 100 Humans!", 35, Color.WHITE, new Color(0,0,0,0));
        GreenfootImage text2 = new GreenfootImage("Press R to Restart", 30, Color.WHITE, new Color(0,0,0,0));
        
        bg.drawImage(text1, (getWidth() - text1.getWidth()) / 2, 150);
        bg.drawImage(text2, (getWidth() - text2.getWidth()) / 2, 220);
    }

    public void act() {
        if(played2 == false) {
            fallingSound.play();
            played2 = true;
        }
        
        if (Greenfoot.isKeyDown("r")) {
            Gorilla.resetInstance();
            Battlefield.resetInstance();
            Currency.coins = 0;
            FireCounter.fireTraps = 0;
            ScoreKeeper.score = 1;
            played2 = false;

            Battlefield battlefield = Battlefield._instance();

            for (Trap trap : battlefield.getObjects(Trap.class)) {
                battlefield.removeObject(trap);
            }

            Greenfoot.setWorld(battlefield);
        }
    }
}