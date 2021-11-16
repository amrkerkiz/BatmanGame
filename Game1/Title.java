import mayflower.*;
import java.awt.Color;
/**
 * Write a description of class Title here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Title extends Stage
{
    private Text title;
    private Text names;
    private Logo logo;
    boolean pressed = false;
    public Title()
    {
        logo = new Logo();
        addActor(logo, 400, 200);
        
        Sound s = new Sound("Sounds/level.wav");
        s.loop();
        
        title = new Text("PRESS ENTER", new Color(255, 255, 255), 80, "Helvetica");
        addActor(title, 130, 400);
        
        names = new Text("By Blake Herrera and Amro Kerkiz", new Color(255, 255, 255), 45, "Helvetica");
        addActor(names, 70, 500);
    }

    public void update() {
        Keyboard kb = getKeyboard();
        if (kb.isKeyPressed("Enter")) {
            if (!pressed) {
                getMayflower().setStage(new Cutscene1(3, 0));
                //getMayflower().setStage(new Cutscene2(3));
                pressed = true;
            }
        }
        
    }
}
