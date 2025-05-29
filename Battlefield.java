import greenfoot.*;

public class Battlefield extends World {
    Label scoreLabel;
    Label waveLabel;

    int waveNumber = 1;
    public boolean ready = false;  // false = waiting for player to start wave
    boolean waveAnnounced = false;
    
    private static Battlefield instance_ = null;
    
    public static Battlefield _instance() {
        if(instance_ == null) {
            instance_ = new Battlefield();
        }
        return instance_;
    }
    
    private Battlefield() {
        super(600, 400, 1);
        
        ScoreKeeper.score = 1;
        
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        Cart cart = new Cart();
        addObject(cart, 25, 40);
        
        Gorilla gorilla = new Gorilla();
        addObject(gorilla, 50, 50);
        
        scoreLabel = new Label(0, 60);
        addObject(scoreLabel, 550, 60);
        
        // Announce wave 1 right away (no spawning yet)
        announceWave();
        waveAnnounced = true;
    }
    
    public void act() {
        scoreLabel.setValue(ScoreKeeper.score);
        
        if (waveCleared() && ready) {
            ready = false;
            waveNumber++;
            announceWave();
            waveAnnounced = true;
        }
        
        // Wait for player to press enter to start the wave
        if (!ready && waveAnnounced && Greenfoot.isKeyDown("enter")) {
            if (waveLabel != null) {
                removeObject(waveLabel);
            }
            startWave(waveNumber);
            ready = true;
            waveAnnounced = false;
        }
    }
    
    public void spawnHuman(int x, int y) {
        Human1 h1 = new Human1();
        addObject(h1, x, y);
    }
    
    public void startWave(int num) {
        switch(num) {
            case 1: wave1(); break;
            case 2: wave2(); break;
            case 3: wave3(); break;
            default: break;
        }
    }
    
    public void wave1() {
        spawnHuman(550, 10);
        spawnHuman(50, 60);
        spawnHuman(550, 110);
        spawnHuman(50, 150);
        spawnHuman(550, 200);
    }
    
    public void wave2() {
        spawnHuman(100, 50);
        spawnHuman(500, 60);
        spawnHuman(100, 110);
        spawnHuman(500, 150);
        spawnHuman(100, 200);
        spawnHuman(500, 250);
    }
    
    public void wave3() {
        spawnHuman(150, 50);
        spawnHuman(450, 100);
        spawnHuman(150, 150);
        spawnHuman(450, 200);
        spawnHuman(150, 250);
        spawnHuman(450, 300);
    }
    
    public boolean waveCleared() {
        // score thresholds for each wave
        if (waveNumber == 1) return ScoreKeeper.score >= 6;
        if (waveNumber == 2) return ScoreKeeper.score >= 12;
        if (waveNumber == 3) return ScoreKeeper.score >= 18;
        return false;
    }
    
    public void announceWave() {
        waveLabel = new Label("Wave " + waveNumber, 60);
        addObject(waveLabel, getWidth() / 2, getHeight() / 2);
    }

    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }
}
