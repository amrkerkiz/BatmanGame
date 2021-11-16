import mayflower.*;
import java.awt.Color;
import java.util.Scanner;
/**
 * Write a description of class Cutscene2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cutscene3 extends Stage
{
    private Text name;
    private Text speech;
    int lives;
    int gscore;
    private talkingBatman batman;
    private Batmobile batmobile;
    public Cutscene3(int livezs, int score)
    {
        lives = livezs;
        name = new Text("", new Color(255, 255, 255), 40, "Impact");
        speech = new Text("", new Color(255, 255, 255), 30, "Helvetica");
        gscore = score;
        batman = new talkingBatman();
        batmobile = new Batmobile();
    }

    public void update() {
        addActor(batmobile, 200, 330);
        batmobile.setPicture("Images/batmobile.gif");
        setBackground("Images/extraDarkFlipped.gif");
        
        addActor(new TalkBox(), 400, 475);
        
        addActor(batman, 120, 475);
        
        addActor(name, 255, 375);
        
        addActor(speech, 255, 440);

        Timer f = new Timer();
        while(f.getTimePassed() < 1000) {
            name.setText("Alfred");
            batman.setPicture("Images/static.gif");
            batman.setPosition(20, 540);
            speech.setText("...Master Wayne...");
        }
        while(f.getTimePassed() < 2500) {
           name.setText("Batman");
           batman.setPicture("Images/talk.gif");
           batman.setPosition(120, 475);
           speech.setText("Alfred!");
        }
        while(f.getTimePassed() < 4000) {
           name.setText("Alfred");
           batman.setPicture("Images/static.gif");
           batman.setPosition(20, 540);
           speech.setText("You must stop Joker!");
        }
        getMayflower().setStage(new Boss(lives, gscore));
    }
}

