import greenfoot.*;

public class EndScreen extends World {

    public EndScreen() {
        super(600, 400, 1);
        GreenfootImage bg = getBackground();
        bg.setColor(Color.BLACK);
        bg.fill();

        GreenfootImage text1 = new GreenfootImage("Gorilla has fallen!", 40, Color.WHITE, new Color(0,0,0,0));
        GreenfootImage text2 = new GreenfootImage("Press R to Restart", 30, Color.WHITE, new Color(0,0,0,0));
        
        bg.drawImage(text1, (getWidth() - text1.getWidth()) / 2, 150);
        bg.drawImage(text2, (getWidth() - text2.getWidth()) / 2, 220);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Gorilla.resetInstance();
            Battlefield.resetInstance();
            Currency.coins = 0;
            FireCounter.fireTraps = 0;
            ScoreKeeper.score = 1;

            Battlefield battlefield = Battlefield._instance();

            for (Trap trap : battlefield.getObjects(Trap.class)) {
                battlefield.removeObject(trap);
            }

            Greenfoot.setWorld(battlefield);
        }
    }
}
