import javax.swing.*;
import java.awt.*;

/**
 * Created by weitao on 4/16/15.
 */
public class UI_Flow extends JFrame{
    JButton button1,button2,button3,button4,button5;

    public static void main(String[] args) {
        new UI_Flow();
    }

    public UI_Flow(){

        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Flow");

        /* FLOW LAYOUT */

        JPanel thePanel = new JPanel();
//        thePanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
//        button1 = new JButton("Button 1");
//        button2 = new JButton("Button 2");
//
//        thePanel.add(button1);
//        thePanel.add(button2);

//        thePanel.setLayout(new BorderLayout());
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");
        button4 = new JButton("Button 4");
        button5 = new JButton("Button 5");
//
//        JPanel thePanel2 = new JPanel();
//        thePanel2.add(button1);
//        thePanel2.add(button2);
//        thePanel.add(thePanel2,BorderLayout.NORTH);
//        thePanel.add(button1,BorderLayout.NORTH);
//        thePanel.add(button2,BorderLayout.SOUTH);
//        thePanel.add(button3,BorderLayout.EAST);
//        thePanel.add(button4,BorderLayout.WEST);
//        thePanel.add(button5,BorderLayout.CENTER);


        /* BOX LAYOUT */

        Box theBox = Box.createHorizontalBox();
        theBox.add(button1);
        theBox.add(Box.createRigidArea(new Dimension(30, 20)));

        theBox.add(button2);
        theBox.add(Box.createHorizontalGlue());
        theBox.add(button3);

        theBox.add(button4);

        this.add(theBox);
        this.setVisible(true);
    }
}
