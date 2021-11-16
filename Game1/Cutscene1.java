import mayflower.*;
import java.awt.Color;
import java.util.Scanner;
/**
 * Write a description of class Cutscene2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cutscene1 extends Stage
{
    private Text name;
    public Cutscene1(int livezs, int score)
    {
        name = new Text("", new Color(255, 255, 255), 90, "Impact");
    }

    public void update() {
        setBackground("Images/extraDarkFlipped.gif");
        addActor(name, 150, 460);
        Timer f = new Timer();
        while(f.getTimePassed() < 4000) {
            name.setText("Gotham, 1993");
        }
        getMayflower().setStage(new Level1(3, 0));
    }
}
