import mayflower.*;
/**
 * Write a description of class Thug here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Thug extends AnimatedActor
{
    private int health;
    private int attackType;
    private Picture runR;
    private Picture a1R;
    private Picture a2R;
    private Picture runL;
    private Picture a1L;
    private Picture a2L;
    private Timer timer;
    private Batman damn;
    private String man;
    
    private boolean moving;
    private boolean attacking;
    private boolean canMove;
    
    private int cp;
    
    public Thug(Batman x, int checkpoint)
    {
        health = 10;
        attackType = 0;
        timer = new Timer();
        damn = x;
        man = "L";
        canMove = false;
        a1R = new Picture("Images/thug/thugA1.gif");
        a2R = new Picture("Images/thug/thugA2.gif");
        runR = new Picture("Images/thug/thugR.gif");
        runL = new Picture("Images/thug/thugL.gif");
        setPicture(runL);
        moving = false;
        attacking = false;
        cp = checkpoint;
    }
    public void update() {
       setDrawOrder(100);
       super.update();
       if (canMove) {
           for (Actor a : this.getTouching()) {
               if(thugPos().equals("right") && timer.getTimePassed() > 1500) {
                  move(1, "West");
                  setPicture("Images/thug/thugL.gif");
                  attacking = false;
                  man = "L";
               }
               else if(thugPos().equals("left") && timer.getTimePassed() > 1500) {
                  move(1, "East");
                  setPicture("Images/thug/thugR.gif");
                  attacking = false;
                  man = "R";
               }
               else if (a instanceof Batman && timer.getTimePassed() > 1500) { //if touching batman
                    attackType = (int)(Math.random()*2)+1;
                    
                    setPicture("Images/thug/thugA"+attackType+man+".gif"); //add direction to attack
                    Batman bat = (Batman)a;
                    if (attackType == 1) {
                        bat.setHealth(-10);
                    }
                    if (attackType == 2) {
                        bat.setHealth(-5);
                    }
                    timer = new Timer();
                    moving = false;
                    attacking = true;
                }
           }
       }
    }
    public boolean isAttacking() {
        return attacking;
    }
    public String thugPos() { //returns position of thug to batman
        if(this.getX() >= damn.getX()+100) {
            return "right";
        }
        else if(this.getX() < damn.getX()-100) {
            return "left";
        }
        else {
            return "on";
        }
    }
    public void changeHealth(int change) {
        health += change;
    }
    public int getHealth() {
        return health;
    }
    public void setMovability(boolean x) {
        canMove = x;
    }
    public int getCp() {
        return cp;
    }   
}
