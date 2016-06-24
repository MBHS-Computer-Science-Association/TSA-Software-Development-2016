package org.ecclesia.demo.graphics;

import java.awt.*;
import javax.swing.*;

public class DisplayInfo {

    public static void display() {

        Runnable r = new Runnable() {
            public void run() {
                String pt1 = "<html><body width='";
                String pt2 =
                    "'><h1>Demonstration Info</h1>" +
                    "<p>This demo presents you with the training of a neural network &amp;" +
                    " allows simple user interactivity.  By clicking repeatedly from any random point"
                    + " and back again to the same point, you are training the neural network." +
                    " The green dots are the random points you have inputted into the network, and the orange"
                    + "dot is the predicted output of the whole program after constant training of" +
                    " the component.<br><br>" +
                    "<p>Although this demonstration shows our neural network, it is implementing a slow learning curve for accurate  " +
                    "";
                String pt3 =
                    " results therefore it would take time to train our program. We have a second demonstration that has a lower learning curve therefore learns faster" +
                    "";

                //JPanel p = new JPanel( new BorderLayout() );

                int width = 300;
                String s = pt1 + width + pt2 + pt3 ;

               JOptionPane.showMessageDialog(null, s);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
