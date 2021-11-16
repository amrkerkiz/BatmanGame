import mayflower.*;
/**
 * Write a description of class GravityActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GravityActor extends Actor {
    private boolean falling;
    public GravityActor()
    {
        falling = true;
    }
    public void update()
    {
       if (isGrounded()) {
           //a
       }
       else {
           move(7, "South");
           falling = true;
       }
    }
    public boolean isGrounded() {
       for (Actor a : this.getTouching()) {
           if (a instanceof Ground) {
               return true;
           }
       }
       return false;
    }
    public boolean isFalling() {
        return falling;
    }
}
