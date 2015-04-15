
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.geom.Arc2D;
import java.text.NumberFormat;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 * Created by weitao on 4/13/15.
 */
public class UI_Calculator extends JFrame {
    JButton button;
    JLabel label1, label2, label3;
    JTextField textField1, textField2;
    JCheckBox dollarSign, commaSeparator;
    JRadioButton addNums, subtractNums, MultiplyNums, divideNums;
    JSlider howManyTimes;

    double number1, number2, totalCalc;

    public static void main(String[] args) {
        new UI_Calculator();
    }

    public UI_Calculator() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator");
        JPanel thePanel = new JPanel();
        button = new JButton("Calculate");
        ListenForButton listenForButton = new ListenForButton();
        button.addActionListener(listenForButton);
        thePanel.add(button);
        label1 = new JLabel("Number 1");
        thePanel.add(label1);
        textField1 = new JTextField("",5);
        thePanel.add(textField1);
        label2 = new JLabel("Number 2");
        thePanel.add(label2);
        textField2 = new JTextField("",5);
        thePanel.add(textField2);
        dollarSign = new JCheckBox("Dollars");
        commaSeparator = new JCheckBox("Commas");
        thePanel.add(dollarSign);
        thePanel.add(commaSeparator,true);
        addNums = new JRadioButton("Add");
        subtractNums = new JRadioButton("Subtract");
        MultiplyNums = new JRadioButton("Multiply");
        divideNums = new JRadioButton("Divide");

        ButtonGroup operation = new ButtonGroup();
        operation.add(addNums);
        operation.add(subtractNums);
        operation.add(MultiplyNums);
        operation.add(divideNums);

        JPanel operPanel = new JPanel();

        Border operBorder = BorderFactory.createTitledBorder("Operation");
        operPanel.setBorder(operBorder);
        operPanel.add(addNums);
        operPanel.add(subtractNums);
        operPanel.add(MultiplyNums);
        operPanel.add(divideNums);

        addNums.setSelected(true);

        thePanel.add(operPanel);

        label3 = new JLabel("Perform How Many Times");

        thePanel.add(label3);

        howManyTimes = new JSlider(1,99,1);
        howManyTimes.setMinorTickSpacing(1);
        howManyTimes.setMajorTickSpacing(10);
        howManyTimes.setPaintTicks(true);
        howManyTimes.setPaintLabels(true);
        ListenForSlider listenForSlider = new ListenForSlider();
        howManyTimes.addChangeListener(listenForSlider);
        thePanel.add(howManyTimes);
        this.add(thePanel);
        this.setVisible(true);
        textField1.requestFocus();

    }

    // Implement Listeners

    private class ListenForSlider implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if(e.getSource()==howManyTimes){
                label3.setText("Perform how many times?"+howManyTimes.getValue());
            }
        }
    }


    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button){
                try {
                    number1 = Double.parseDouble(textField1.getText());
                    number2 = Double.parseDouble(textField2.getText());
                }
                catch (NumberFormatException excep){
                    JOptionPane.showMessageDialog(UI_Calculator.this,"Please enter the right information","Error",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                if(addNums.isSelected()){
                    totalCalc = addNumbers(number1,number2,howManyTimes.getValue());
                } else if(subtractNums.isSelected()){
                    totalCalc = subtractNums(number1, number2, howManyTimes.getValue());
                } else if(MultiplyNums.isSelected()){
                    totalCalc = multiplyNums(number1, number2, howManyTimes.getValue());
                } else {
                    totalCalc = divideNums(number1, number2, howManyTimes.getValue());
                }
                if(dollarSign.isSelected()){
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                    JOptionPane.showMessageDialog(UI_Calculator.this,numberFormat.format(totalCalc),"Solution",JOptionPane.INFORMATION_MESSAGE);
                } else if(commaSeparator.isSelected()) {
                    NumberFormat numberFormat = NumberFormat.getNumberInstance();
                    JOptionPane.showMessageDialog(UI_Calculator.this, numberFormat.format(totalCalc), "Solution", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UI_Calculator.this, totalCalc, "Solution", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }

    public static double addNumbers(double a, double b, int howmanytimes){
        double total = 0;
        int i=1;
        while(i<=howmanytimes){
            total += a+b;
            i++;
        }
        return total;
    }
    private static double subtractNums(double a, double b, int howmanytimes){
        double total = 0;
        int i=1;
        while(i<=howmanytimes){
            total += a-b;
            i++;
        }
        return total;
    }
    private static double multiplyNums(double a, double b, int howmanytimes){
        double total = 0;
        int i=1;
        while(i<=howmanytimes){
            total += a*b;
            i++;
        }
        return total;
    }
    private static double divideNums(double a, double b, int howmanytimes){
        double total = 0;
        int i=1;
        while(i<=howmanytimes){
            total += a/b;
            i++;
        }
        return total;
    }
}
