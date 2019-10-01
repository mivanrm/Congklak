import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.awt.*;

public class Hole extends JLabel {
    ImageIcon seed;
    int position;
    int seedcount;
    public Hole(int pos, int count){
        this.position = pos;
        this.seedcount = count;

        seed = new ImageIcon (this.getClass().getResource("res/mancala hole 2/"+Integer.toString(seedcount)+".png"));
        setPosition(this.position);
        setIcon(getScaledImage(seed,70,70));
        
    }

    private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
        Image image = srcImg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

    public void setPosition(int position){
        switch (position){

            case 0:
            //636,274
            setBounds(636,274,70,70);
            break;

            case 1:
            //548,274
            setBounds(548,274,70,70);
            break;

            case 2:
            //458,274
            setBounds(458,274,70,70);
            break;

            case 3:
            //366,274
            setBounds(366,274,70,70);
            break;

            case 4:
            //276,274
            setBounds(276,274,70,70);
            break;

            case 5:
             //186,274
             setBounds(186,274,70,70);
            break;
           
            case 6:
            //96,274
            setBounds(96,274,70,70);
            break;

            case 8:
            //96,190
            setBounds(96,190,70,70);
            break;

            case 9:
            //186,190
            setBounds(186,190,70,70);
            break;

            case 10:
            //276,190
            setBounds(276,190,70,70);
            break;

            case 11:
            //366,190
            setBounds(366,190,70,70);
            break;

            case 12:
            //458,190
            setBounds(458,190,70,70);
            break;

            case 13:
            //548,190
            setBounds(548,190,70,70);
            break;

            case 14:
            //636,190
            setBounds(636,190,70,70);
            break;

        }
    }

    public int getPosition(){
        return this.position;
    }

    public int getSeedCount(){
        return this.seedcount;
    }
    public void setSeedCount(int count){
        this.seedcount = count;
    }
    public String toString(){
        return "Hole : " + this.position + " Seed Count : " + this.seedcount;
    }
}