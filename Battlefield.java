import greenfoot.*;

public class Battlefield extends World {
    Label scoreLabel;
    Label waveLabel;
    Label coinLabel;
    Label fireLabel;
    Label finalLabel;
    
    Cart cart = new Cart();
    GreenfootSound startSound = new GreenfootSound("battle_horn_1-6931.mp3");
    GreenfootSound finalSound = new GreenfootSound("medieval-horn-by-kris-klavenes-wav-77565.mp3");
    
    int waveNumber = 1;
    public boolean ready = false; 
    boolean waveAnnounced = false;
    boolean coinsGiven = false;
    public static boolean waveStarted = false;
    
    public static boolean deadShown = false;
    public static boolean newShown = false;
    
    private static Battlefield instance_;
    public static boolean finalWave = false;
    public static boolean finalEnter = false;
    Label start = new Label("Press enter to face enemies", 30);
    TimedLabel deadLabel = new TimedLabel("Gorilla has Fallen!", 60, 120);
    TimedLabel newStart = new TimedLabel("Press R to Restart", 30, 120);
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
        
        addObject(cart, 25, 40);
        addObject(Gorilla.getInstance(), 50, 50);
        
        scoreLabel = new Label(0, 60);
        addObject(scoreLabel, 550, 60);
        
        coinLabel = new Label("" + Currency.getCoins(), 40);
        addObject(coinLabel, 50, 360);
        
        Coin coin = new Coin();
        addObject(coin, 15, 362);
        
        FireCount fire = new FireCount();
        addObject(fire, 90, 360);
        
        fireLabel = new Label("" + FireCounter.getTraps(), 40);
        addObject(fireLabel, 120, 360);
        
        announceWave();
        waveAnnounced = true;
        prepare();
    }
    
    public void prepare() {
        Gorilla gorilla = Gorilla.getInstance();
        if (gorilla.getWorld() != null) {
            gorilla.getWorld().removeObject(gorilla);
        }
        addObject(gorilla, 50, 50);
        gorilla.updateHealthBarPosition();
    }
    
    boolean waveInProgress = false;

    public void act() {
        scoreLabel.setValue(ScoreKeeper.score);
        coinLabel.setValue("" + Currency.getCoins());
        fireLabel.setValue("" + FireCounter.getTraps());
        
        if (Gorilla.dead && !deadShown && !newShown) {
            deadShown = true;
            newShown = true;
            addObject(deadLabel, 300, 200);
            addObject(newStart, 300, 250);
        }
        
        if (deadShown && newShown && Greenfoot.isKeyDown("r")) {
            //reset all game properties
            deadShown = false;
            newShown = false;
            Gorilla.hasPet = false;
            Battlefield.waveStarted = false;
            Ship.played = true;
            Gorilla.isDead = false;
            Gorilla.dead = false;
            Gorilla.resetInstance();
            Battlefield.resetInstance();
            Currency.coins = 100;
            FireCounter.fireTraps = 0;
            ScoreKeeper.score = 1;
            //new bf 
            Battlefield bf = new Battlefield();        
            Greenfoot.setWorld(bf);
        }
        // Announce final wave only if not announced and no wave in progress
        if (finalWave && !waveAnnounced && !waveInProgress) {
            announceWave2();
            waveAnnounced = true;
        }
        // Announce normal wave only if cleared, ready, no wave in progress
        else if (!finalWave && waveCleared() && ready && !waveInProgress) {
            if (!coinsGiven) {
                giveWaveReward(waveNumber);
                coinsGiven = true;
            }
            ready = false;
            waveNumber++;
            if (waveNumber == 9) {
                finalWave = true;
                return;
            }
            announceWave();
            waveAnnounced = true;
        }
    
        // Start final wave when enter pressed
        if (!ready && finalWave && waveAnnounced && !waveInProgress && Greenfoot.isKeyDown("enter")) {
            if (finalLabel != null && finalLabel.getWorld() != null) {
                finalSound.play();
                waveStarted = true;
                removeObject(finalLabel);
                finalLabel = null;
                removeObject(start);
                removeObject(cart);
                waveAnnounced = false; // This will only allow a new announce once waveInProgress is false
                startWave(waveNumber);
                waveInProgress = true;  // Mark wave started
                ready = true;
            }
        }
        // Start normal wave when enter pressed
        else if (!ready && waveAnnounced && !finalWave && !waveInProgress && Greenfoot.isKeyDown("enter")) {
            if (waveLabel != null && waveLabel.getWorld() != null) {
                startSound.play();
                waveStarted = true;
                removeObject(waveLabel);
                waveLabel = null;
                removeObject(start);
                removeObject(cart);
                waveAnnounced = false; // Same as above
                coinsGiven = false;
                startWave(waveNumber);
                waveInProgress = true;
                ready = true;
            }
        }
    
        // When the wave is cleared, mark waveInProgress false so new announce can happen
        if (waveInProgress && waveCleared()) {
            waveInProgress = false;
        }
    }

    private Gorilla gorilla;

    public Gorilla getGorilla() {
        return gorilla;
    }
    
    public void spawnHuman(int x, int y) {
        Human1 h1 = new Human1();
        addObject(h1, x, y);
    }
    
    public void spawnHuman2(int x, int y) {
        Human2 h2 = new Human2();
        addObject(h2, x, y);
    }
    
    public void startWave(int num) {
        switch(num) {
            case 1: wave1(); break;
            case 2: wave2(); break;
            case 3: wave3(); break;
            case 4: wave4(); break;
            case 5: wave5(); break;
            case 6: wave6(); break;
            case 7: wave7(); break;
            case 8: wave8(); break;
            case 9: wave9(); break;
            default: break;
        }
    }
    
     public void wave1() {
        Battlefield world = this;
        //total enemies: 6
        world.addObject(new DelayedSpawner(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(30, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(90, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(120, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }

    public void wave2() {
        Battlefield world = this;    
        //total enemies: 12
        world.addObject(new DelayedSpawner(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner2(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(120, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner2(180, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(240, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner2(300, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }
    
    public void wave3() {
        Battlefield world = this;
        //total enemies: 20
        world.addObject(new DelayedSpawner2(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }
    
    public void wave4() {
        Battlefield world = this;
        //total enemies: 30
        world.addObject(new DelayedSpawner(0, 100, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 300, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 100, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 200, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 100, 350), 0, 0);
        world.addObject(new DelayedSpawner(0, 300, 350), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 350), 0, 0);
        world.addObject(new DelayedSpawner(0, 400, 200), 0, 0);
    }
    
    public void wave5() {
        Battlefield world = this;
        //total enemies: 35
        world.addObject(new DelayedSpawner2(0, 50, 50), 0, 0);
        world.addObject(new DelayedSpawner2(60, 150, 50), 0, 0);
        world.addObject(new DelayedSpawner2(120, 250, 50), 0, 0);
        world.addObject(new DelayedSpawner2(180, 350, 50), 0, 0);
        world.addObject(new DelayedSpawner2(240, 450, 50), 0, 0);
    }
    
    public void wave6() {
        Battlefield world = this;
        //total enemies: 60
        world.addObject(new DelayedSpawner(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(120, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(180, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(240, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(300, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(360, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(420, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(480, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(540, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(600, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(660, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(720, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(780, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(840, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(900, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(960, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1020, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1080, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1140, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1200, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1260, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1320, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1380, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1340, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }
    
    public void wave7() {
        Battlefield world = this;
        //total enemies: 75
        world.addObject(new DelayedSpawner(0, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner(60, 100, 50), 0, 0);
        world.addObject(new DelayedSpawner(60, 100, 125), 0, 0);
        world.addObject(new DelayedSpawner(60, 100, 200), 0, 0);
        world.addObject(new DelayedSpawner(60, 100, 275), 0, 0);
        world.addObject(new DelayedSpawner(60, 100, 350), 0, 0);
        
        world.addObject(new DelayedSpawner2(180, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(240, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(300, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(360, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(420, 500, 350), 0, 0);
    }
    
    public void wave8() {
        Battlefield world = this;
        //total enemies: 90
        world.addObject(new DelayedSpawner2(0, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(60, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(240, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(200, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner2(360, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(420, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(480, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(540, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(600, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner2(660, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(720, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(780, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(840, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(900, 500, 350), 0, 0);
    }
    
    public void wave9() {
        Battlefield world = this;
        
        world.addObject(new DelayedSpawner(0, 300, 200), 0, 0);
    }
    
    public boolean waveCleared() {
        if (waveNumber == 1) return ScoreKeeper.score >= 6;
        if (waveNumber == 2) return ScoreKeeper.score >= 12;
        if (waveNumber == 3) return ScoreKeeper.score >= 20;
        if (waveNumber == 4) return ScoreKeeper.score >= 30;
        if (waveNumber == 5) return ScoreKeeper.score >= 35;
        if (waveNumber == 6) return ScoreKeeper.score >= 60;
        if (waveNumber == 7) return ScoreKeeper.score >= 75;
        if (waveNumber == 8) return ScoreKeeper.score >= 90;
        return false;
    }

    public void giveWaveReward(int wave) {
        if (wave == 1) Currency.addCoins(10);
        if (wave == 2) Currency.addCoins(20);
        if (wave == 3) Currency.addCoins(20);
        if (wave == 4) Currency.addCoins(30);
        if (wave == 5) Currency.addCoins(40);
        if (wave == 6) Currency.addCoins(60);
        if (wave == 7) Currency.addCoins(80);
        if (wave == 8) Currency.addCoins(100);
    }

    public void announceWave2() {
        finalLabel = new Label("Final Wave", 60);
        addObject(finalLabel, getWidth() / 2, getHeight() / 2);
        addObject(start, 300, 250);
        addObject(cart, 25, 40);
        waveStarted = false;
    }
    
    public void announceWave() {
        waveLabel = new Label("Wave " + waveNumber, 60);
        addObject(waveLabel, getWidth() / 2, getHeight() / 2);
        addObject(start, 300, 250);
        addObject(cart, 25, 40);
        waveStarted = false;
    }
    
    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }
    
    public static void resetInstance() {
        instance_ = null;
    }
}