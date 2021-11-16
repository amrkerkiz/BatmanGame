import mayflower.*;
/**
 * Write a description of class Checkpoint here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Checkpoint extends Actor
{
    private int cp;
    public Checkpoint(int checkpoint) {
        cp = checkpoint;
    }
    public void update() {
        setPicture("Images/checkpoint.png");
    }
    public int getCheckpoint() {
        return cp;
    }
}
