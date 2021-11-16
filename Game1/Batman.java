import mayflower.*;
/**
 * Write a description of class MovableAnimatedActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Batman extends GravityActor
{
    private String move;
    private String direction;
    private String attackType;
    
    private boolean batAtEdge;
    
    private int health;
    private int score;
    private int lives;
    
    private int cpNum; //checkpoint number
    
    private Picture idleL;
    private Picture idleR;
    private Picture runL;
    private Picture runR;
    private Picture jumpL;
    private Picture jumpR;
    private Picture fallL;
    private Picture fallR;
    private Picture dead;
    private Picture attackL;
    private Picture attackR;
    private Timer timer;
    private Timer attackTimer;
    
    private HealthBar hBar;
    
    private boolean jump;
    
    public Batman(int livesB)
    {
        move = "idle";
        direction = "right";
        attackType = "";
        batAtEdge = false;
        
        cpNum = 0;
        
        health = 100;
        score = 0;
        lives = livesB;
        
        timer = new Timer();
        attackTimer = new Timer();
        
        jump = false;
        
        idleL = new Picture("Images/idleL.gif");
        idleR = new Picture("Images/idleR.gif");
        runL = new Picture("Images/runL.gif");
        runR = new Picture("Images/runR.gif");
        jumpL = new Picture("Images/jumpL.gif");
        jumpR = new Picture("Images/jumpR.gif");
        fallL = new Picture("Images/fallL.gif");
        fallR = new Picture("Images/fallR.gif");
        attackL = new Picture("Images/attackL.gif");
        attackR = new Picture("Images/attackR.gif");
        dead = new Picture("Images/dead.gif");
        //attackL = new Picture("Images/attackL.gif");
        //attackR = new Picture("Images/attackR.gif");
    }
    public void update() {
        super.update();
        
        setDrawOrder(99);
        //Keymaps
        
        Keyboard kb = getKeyboard();
        
        //Movement
        
        if (kb.isKeyPressed("Right") && !isTouchingEdge("East")) {
            direction = "right";
            move = "run";
        }
        else if (kb.isKeyPressed("Left") && !isTouchingEdge("West")) {
            direction = "left";
            move = "run";
        }
        else if (!isGrounded()) {
            move = "fall";
        }
        else {
            move = "idle";
        }
        
        if(kb.isKeyPressed("z") && attackTimer.getTimePassed() > 300) {
            move = "attack";
            attackTimer = new Timer();
        }
        
        if (kb.isKeyPressed("Space") && timer.getTimePassed() > 300) {
            jump = true;
        }
        
        if (direction.equals("right")) {
            if (move.equals("run")) {
                setPicture(runR);
                if(getStage() instanceof Level1) {
                    Level1 a = (Level1)getStage();
                    if (!a.isScrolling1()) {
                        move(4, "East");
                    }
                }
                if(getStage() instanceof Level2) {
                    Level2 b = (Level2)getStage();
                    if (!b.isScrolling2()) {
                        move(4, "East");
                    }
                }
                if(getStage() instanceof Boss) {
                    Boss b = (Boss)getStage();
                    if (!b.isScrolling2()) {
                        move(4, "East");
                    }
                }
                if (jump) {
                    move(100, "North");
                    jump = false;
                    timer = new Timer();
                }
            }
            else if (jump) {
                move(100, "North");
                setPicture(jumpR);
                jump = false;
                timer = new Timer();
            }
            else if (move.equals("fall")) {
                setPicture(fallR);
            }
            else if (move.equals("attack")) {
                setPicture(attackR);
                for (Actor a : this.getTouching()) {
                    if (a instanceof Thug && attackTimer.getTimePassed() <= 150) { //if touching batman
                        Thug t = (Thug)a;
                        t.changeHealth(-5); //thugs have 10 health
                        System.out.println("Thug Health: "+t.getHealth());
                    }
                    if (a instanceof Joker && attackTimer.getTimePassed() <= 150) { //if touching batman
                        Joker t = (Joker)a;
                        t.changeHealth(-15); //thugs have 10 health
                        System.out.println("Joker Health: "+t.getHealth());
                    }
                }
            }
            else {
                setPicture(idleR);
            }
        }
        if (direction.equals("left")) {
            if (move.equals("run")) {    
                move(4, "West");
                setPicture(runL);
                if (jump) {
                    move(100, "North");
                    jump = false;
                    timer = new Timer();
                }
            }
            else if (jump) {
                move(100, "North");
                setPicture(jumpL);
                jump = false;
                timer = new Timer();
            }
            else if (move.equals("fall")) {
                setPicture(fallL);
            }
            else if (move.equals("attack")) {
                setPicture(attackL);
                for (Actor a : this.getTouching()) {
                    if (a instanceof Thug && attackTimer.getTimePassed() <= 150) { //if touching batman
                        Thug t = (Thug)a;
                        t.changeHealth(-5); //thugs have 10 health
                        System.out.println("Thug Health: "+t.getHealth());
                    }
                    if (a instanceof Joker && attackTimer.getTimePassed() <= 150) { //if touching batman
                        Joker t = (Joker)a;
                        t.changeHealth(-15); //thugs have 10 health
                        System.out.println("Joker Health: "+t.getHealth());
                    }
                }
            }
            else {
                setPicture(idleL);
            }
        }
        if (health <= 0) {
            setPicture(dead);
            lives--;
            if(getStage() instanceof Level1) {
                getMayflower().setStage(new Level1(lives, score));
            }
            if(getStage() instanceof Level2) {
                getMayflower().setStage(new Level2(lives, score));
            }
            if(getStage() instanceof Boss) {
                getMayflower().setStage(new Boss(lives, score));
            }
        }
        if (lives <= 0) {
            getMayflower().setStage(new GameOver());
        }
        if (hBar == null) {
            hBar = new HealthBar();
            getStage().addActor(hBar, 270, 50);
        }
        if (attackTimer.getTimePassed() <= 150) {
            if(direction.equals("right")) {
                setPicture(attackR);
            }
            else {
                setPicture(attackL);
            }
        }
        for (Actor a : this.getTouching("South")) {
            if (a instanceof Checkpoint) {
                Checkpoint c = (Checkpoint)a;
                cpNum = c.getCheckpoint();
                System.out.println("Touching checkpoint "+cpNum);
            }
        }
    }
    public boolean checkMotion(String d, String m) {
        if (d.equals(direction) && m.equals(move)) {
            return true;
        }
        return false;
    }
    public String getDirection() {
        return direction;
    }
    public boolean isMoving(){
       return (move.equals("run")); 
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int change) {
         health += change;
         hBar.changeHealth(health);
    }
    public int getLives() {
        return lives;
    }
    public void changeLives(int change) {
        lives += change;
    }
    public int getScore() {
        return score;
    }
    public void changeScore(int change) {
        score += change;
    }
    public int getCpNum() {
        return cpNum;
    }
}
