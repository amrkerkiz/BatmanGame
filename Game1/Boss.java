import mayflower.*;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Level1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Boss extends Stage
{
    private Text healthT;
    private Text livesT;
    private Text scoreT;
    private HealthBar shield;
    
    private ParallaxBackground bg;
    private InfoBar infoImg;
    private Batman bats;
    private String[][] tiles;
    private List<Ground> grounds;
    private List<Thug> thugs;
    private List<Checkpoint> cps;
    private List<PowerUp> powerUps;
    private boolean scrolling2;
    private int checkpointNum; //assigns each cp a num for list
    private Batmobile batmobile = new Batmobile();
    private Joker joker;
    public Boss(int lives, int score)
    {
        //showBounds(true);
        
        setBackground("Images/extraDarkFlipped.gif");
        
        scrolling2 = false;
        grounds = new ArrayList<Ground>();
        thugs = new ArrayList<Thug>();
        cps = new ArrayList<Checkpoint>();
        powerUps = new ArrayList<PowerUp>();
        
        bats = new Batman(lives);
        addActor(bats, 100, 200);
        
        bats.changeScore(score);
        
        bg = new ParallaxBackground();
        addActor(bg, 410, 230);
        
        infoImg = new InfoBar();
        addActor(infoImg, 397, 50);
        
        shield = new HealthBar();
        shield.setToCover();
        shield.setDrawOrder(98);
        addActor(shield, 265, 50);
        
        scoreT = new Text("Score: ", Color.yellow, 30, "Helvetica");
        addActor(scoreT, 450, 25);
        
        //healthT = new Text("Health: ", Color.red, 30, "Helvetica");
        //addActor(healthT, 120, 25);
        
        livesT = new Text("Lives: ", Color.yellow, 30, "Helvetica");
        addActor(livesT, 670, 25);
        
        //set the level up
        
        tiles = new String[5][25];
        /*
         *  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  [ ][ ][ ][ ][ ][ ][c][ ][ ][ ][ ][t][ ][ ][c][ ][ ][ ][t][c][ ][ ][ ][ ][m]
         *  [g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g][g]
         */ 
        for(int r = 0; r < tiles.length; r++) {
            for(int c = 0; c < tiles[0].length; c++) {
                tiles[r][c] = "";
            }
        }
        
        for (int i = 0; i < tiles[0].length; i++) {
            tiles[4][i] = "ground";
            tiles[3][6] = "checkpoint";
            tiles[3][14] = "checkpoint";
            tiles[3][19] = "checkpoint";
            tiles[3][12] = "thug";
            tiles[3][18] = "thug";
            tiles[3][24] = "batmobile";
        }

        //draw tiles
        
        for(int r = 0; r < tiles.length; r++) {
            for(int c = 0; c < tiles[0].length; c++) {
                if(tiles[r][c].equals("ground")) {
                    addActor(groundMaker(), (c)*160+80, (r)*120+40);
                }
                if(tiles[r][c].equals("checkpoint")) {
                    addActor(cpMaker(), (c)*160+80, (r)*120+40);
                }
                if(tiles[r][c].equals("thug")) {
                    addActor(thugMaker(), (c)*5+840, 300);
                }
                if(tiles[r][c].equals("batmobile")) {
                    addActor(batmobile, 3900, 405);
                }
            }
        }
        addActor(thugMaker(1), 950, 300);
        addActor(thugMaker(1), 1080, 300);
        addActor(thugMaker(1), 1100, 300);
        addActor(thugMaker(1), 1200, 300);
        
        joker = new Joker(bats, 3);
        addActor(joker, 3100, 300);
        //addActor(new Thug(), 100, 100);
        
    }
    public Checkpoint cpMaker() {
        checkpointNum++;
        Checkpoint c = new Checkpoint(checkpointNum);
        cps.add(c);
        return c;
    }
    public Ground groundMaker() {
        Ground g = new Ground();
        grounds.add(g);
        return g;
    }
    int check = 0;
    public Thug thugMaker() { //for one thug
        check++;
        Thug t = new Thug(bats, check);
        thugs.add(t);
        return t;
    }
    public Thug thugMaker(int ckpt) { //for multiple thugs
        Thug t = new Thug(bats, ckpt);
        thugs.add(t);
        return t;
    }
    public PowerUp powerUpMaker() {
        int rand = (int)(Math.random()*10)+1;
        PowerUp p;
        if(rand == 10) {
            p = new ExtraLife();
        }
        else if(rand == 9) {
            p = new HealthBoost();
        }
        else {
            p = new ScoreBoost();
        }
        powerUps.add(p);
        return p;
    }
    int count = 0;
    public void update() {
        if (bats.getX() > 400 && count < 640 && bats.checkMotion("right", "run")) { //600 number of times loop iterates before last block passes edge
            bg.move(0.1, "West");
            scrolling2 = true;
            count++;
            batmobile.move(5, "West");
            joker.move(5, "West");
            for(int i = 0; i < grounds.size(); i++) {
                Ground g = grounds.get(i);
                g.move(5, "West");
            }
            for (int j = 0; j < thugs.size(); j++) {
                Thug t = thugs.get(j);
                if (t.isAttacking()) {
                    t.move(5, "West");
                }
            }
            for (int k = 0; k < cps.size(); k++) {
                Checkpoint c = cps.get(k);
                c.move(5, "West");
            }
            for (int l = 0; l < powerUps.size(); l++) {
                PowerUp e = powerUps.get(l);
                e.move(5, "West");
            }
        }
        else {
            scrolling2 = false;
        }
        
        for (int i = 0; i < thugs.size(); i++) {
            System.out.println("cpNum = "+bats.getCpNum());
            if (bats.getCpNum() == thugs.get(i).getCp()) {
                thugs.get(i).setMovability(true);
            }
            if (checkIfDead(thugs.get(i))) {
                thugs.remove(i);
            }
        }
        
        if(bats.getCpNum() == 3) {
            joker.setMovability(true);
        }
        
        scoreT.setText("Score: "+bats.getScore());
        
        //healthT.setText("Health: "+bats.getHealth());
        
        livesT.setText("Lives: "+bats.getLives());
        
        for(Actor a : bats.getTouching()) {
            if (a instanceof ExtraLife) {
                bats.changeLives(1);
                removeActor(a);
            }
            if (a instanceof ScoreBoost) {
                bats.changeScore((int)(Math.random()*1000)+100);
                removeActor(a);
            }
            if (a instanceof HealthBoost) {
                bats.setHealth(100-bats.getHealth());
                removeActor(a);
            }
            if (a instanceof Batmobile) {
                Timer q = new Timer();
                removeActor(bats);
                while (q.getTimePassed() < 1000) {
                    Timer w = new Timer();
                    while(w.getTimePassed() < 20) {
                        //do nothing
                    }
                    batmobile.move(10, "East");
                    System.out.println("moving batmobile");
                }
                getMayflower().setStage(new YouWin());
            }
        }
        
        if(joker.getHealth() <= 0) {
            checkIfDead(joker);
        }
        System.out.println(joker.getHealth());
    }
    
    public boolean isScrolling2() {
        return scrolling2;
    }
    
    public boolean checkIfDead(Actor a) {
        if (a instanceof Thug) {
            Thug t = (Thug)a;
            if (t.getHealth() <= 0) {
                bats.changeScore(100);
                if((Math.random()*10)+1 > 3) {
                    addActor(powerUpMaker(), t.getX()+100, t.getY()+20);
                }
                removeActor(t);
                return true;
            }
        }
        if (a instanceof Joker) {
            Joker t = (Joker)a;
            if (t.getHealth() <= 0) {
                bats.changeScore(10000);
                addActor(powerUpMaker(), t.getX()+100, t.getY()+20);
                removeActor(t);
                return true;
            }
        }
        return false;
    }
}

