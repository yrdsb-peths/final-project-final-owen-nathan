import greenfoot.*;

/**
 * Tutorial world with guided cutscene and events.
 */
public class Tutorial extends World {
    public boolean landed = false;
    public boolean crossed = false;
    public boolean spawn1 = true;

    Label text = new Label("a gorilla finds himself stranded in a new world...", 32);
    Label wasdLabel = new Label("WASD to move", 50); 
    Label text2 = new Label("Press enter to meet friends", 50);
    Label text3 = new Label("'friends' turn out to be hostile gorilla like beings...", 32);
    Label text4 = new Label("Press space to attack", 50);
    Label text5 = new Label("Check out the shop!", 50);
    Label scoreLabel;

    private boolean enterPressed = false;
    private boolean text3Shown = false;
    private boolean text4Shown = false;
    private boolean shopShown = false;
    private boolean wasdShown = false;
    private boolean enemySpawned = false;
    private boolean enemyKilled = false;
    private Label currentLabel = null;

    public Tutorial() {
        super(600, 400, 1);
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);

        scoreLabel = new Label(0, 60);
        addObject(scoreLabel, 550, 60);

        switchLabel(text);

        Ship asteroid = new Ship();
        addObject(asteroid, Ship.x, 0);
    }

    public void act() {
        if (landed && !wasdShown) {
            switchLabel(wasdLabel);
            wasdShown = true;
        }

        if (crossed && currentLabel == wasdLabel) {
            switchLabel(text2);
        }

        if (Greenfoot.isKeyDown("enter")) {
            if (!enterPressed) {
                enterPressed = true;
                if (!text3Shown) {
                    switchLabel(text3);
                    spawnHuman(599, Greenfoot.getRandomNumber(400));
                    enemySpawned = true;
                    text3Shown = true;
                }
            }
        } else {
            enterPressed = false;
        }

        if (text3Shown && Greenfoot.isKeyDown("d") && !text4Shown) {
            switchLabel(text4);
            text4Shown = true;
        }

        if (enemySpawned && !checkEnemies() && !shopShown) {
            switchLabel(text5);
            addObject(new Cart(), 25, 40);
            shopShown = true;
        }
    }

    private void switchLabel(Label newLabel) {
        if (currentLabel != null && currentLabel.getWorld() != null) {
            removeObject(currentLabel);
        }
        currentLabel = newLabel;
        addObject(currentLabel, 300, 300);
    }

    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }

    public void spawnHuman(int x, int y) {
        Human1 h1 = new Human1();
        addObject(h1, x, y);
    }

    public boolean checkEnemies() {
        return !getObjects(Human1.class).isEmpty();
    }
}
