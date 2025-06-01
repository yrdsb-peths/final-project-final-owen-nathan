import greenfoot.*;

public class Battlefield extends World {
    Label scoreLabel;
    Label waveLabel;
    Label coinLabel;

    int waveNumber = 1;
    public boolean ready = false; 
    boolean waveAnnounced = false;
    boolean coinsGiven = false;

    private static Battlefield instance_ = null;
    
    Label start = new Label("Press enter to face enemies", 30);
    
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
        
        coinLabel = new Label("" + Currency.getCoins(), 40);
        addObject(coinLabel, 40, 360);
        
        Coin coin = new Coin();
        addObject(coin, 20, 362);
        
        announceWave();
        waveAnnounced = true;
    }
    
    public void act() {
        scoreLabel.setValue(ScoreKeeper.score);
        coinLabel.setValue("" + Currency.getCoins()); // also fix: use setValue(String)
    
        if (waveCleared() && ready) {
            if (!coinsGiven) {
                giveWaveReward(waveNumber);
                coinsGiven = true;
            }
    
            ready = false;
            waveNumber++;
            announceWave();
            waveAnnounced = true;
        }
    
        if (!ready && waveAnnounced && Greenfoot.isKeyDown("enter")) {
            if (waveLabel != null) {
                removeObject(waveLabel);
            }
            startWave(waveNumber);
            ready = true;
            waveAnnounced = false;
            coinsGiven = false; // Reset so next wave reward can trigger
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
        if (waveNumber == 1) return ScoreKeeper.score >= 6;
        if (waveNumber == 2) return ScoreKeeper.score >= 12;
        if (waveNumber == 3) return ScoreKeeper.score >= 18;
        return false;
    }

    public void giveWaveReward(int wave) {
        if (wave == 1) Currency.addCoins(5);
        if (wave == 2) Currency.addCoins(10);
        if (wave == 3) Currency.addCoins(20);
    }

    
    public void announceWave() {
        waveLabel = new Label("Wave " + waveNumber, 60);
        addObject(waveLabel, getWidth() / 2, getHeight() / 2);
        addObject(start, 300, 250);
    }

    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }
}
