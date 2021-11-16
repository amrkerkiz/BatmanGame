import mayflower.*;
/**
 * Write a description of class ExtraLife here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ExtraLife extends PowerUp
{
    public ExtraLife()
    {
        setPicture("Images/extraLife.png");
    }
    public void update() {
        super.update();
        setPicture("Images/extraLife.png");
    }
}
