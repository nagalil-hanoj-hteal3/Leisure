import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    //Optimization
    JFrame frame;
    JTextField textField;
    JButton[] numButtons = new JButton[10]; //total of numbers (0-9)
    /*
     * Consists of +, -, *, /, 
     * . (decimal), =, delete, clear, sqrt, ^ (maybe)
     */
    JButton[] funcButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clearButton, negButton;/* , sqrtButton, piButton;*/
    
    JPanel panel;
    Font f = new Font("Helvetica Neue", Font.BOLD, 30);
    double num1 = 0, num2 = 0, result = 0; //set to default values
    char operator; // +,-,*,/,.,=, etc.

    /*
     * Constructor
     */
    Calculator(){
        //for the browser feature
        frame = new JFrame("My Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720,780);
        frame.setLayout(null);
        //for the display tab or calculation bar
        textField = new JTextField();
        textField.setBounds(50, 25, 575, 50);
        textField.setFont(f);
        textField.setEditable(false); //cannot be typed inside the bar
        //buttons to be displayed on calculator
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        negButton = new JButton("+/-");
        //sqrtButton = new JButton("sqrt"); //--> bounds limit would exceed
        //piButton = new JButton("π"); 

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = equButton;
        funcButtons[6] = delButton;
        funcButtons[7] = clearButton;
        funcButtons[8] = negButton;
        //funcButtons[8] = sqrtButton;
        //funcButtons[9] = piButton;

        //functions to be used in the calculator
        for(int i = 0; i < 9/*10*/; i++){
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFont(f);
            funcButtons[i].setFocusable(false);
        }
        //numbers to be used in the calculator
        for(int i = 0; i < 10; i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(f);
            numButtons[i].setFocusable(false);
        }

        negButton.setBounds(50,430,145,50);
        delButton.setBounds(205,430,145,50);
        clearButton.setBounds(360, 430, 145, 50);
        
        //sqrtButton.setBounds(360, 430, 145, 50);
        //piButton.setBounds(515, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 575, 300);
        panel.setLayout(new GridLayout(4,4,10,10));
        //panel.setBackground(Color.GRAY);

        /*
         * For the first row of the calculator
         */
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);
        /*
         * For the second row of the calculator
         */
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        /*
         * For the third row of the calculator
         */
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        /*
         * For the fourht row of the calculator
         */
        panel.add(decButton);
        panel.add(numButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        /*
         * For fifth row of calculator
         */
        //panel.add(sqrtButton);
        //panel.add(piButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clearButton);
        //frame.add(sqrtButton);
        //frame.add(piButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();    
    }

    public void actionPerformed(ActionEvent e)
    {
        //numbers
        for(int i = 0; i < 10; i++){
            if(e.getSource() == numButtons[i]){
                //let the buttons clicked be able to fetch the numbers on the text bar
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decButton){
            //let the program fetch the decimal button on the text bar
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource() == addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource() == subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(e.getSource() == mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(e.getSource() == divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource() == equButton){
            num2 = Double.parseDouble(textField.getText());
            
            switch(operator){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            }
            //display the result calculated
            textField.setText(String.valueOf(result));
            num1 = result; //set as the number once calculated
        }
        if(e.getSource() == clearButton){
            textField.setText("");
        }
        if(e.getSource() == delButton) {
            String s = textField.getText();
            textField.setText("");
            for(int i = 0; i < s.length() - 1; i++){
                textField.setText(textField.getText() + s.charAt(i)); //delete
            }
        }
        if(e.getSource() == negButton){
            double temp = Double.parseDouble(textField.getText());
            temp *= -1; //make negative
            textField.setText(String.valueOf(temp)); //display onto the bar tab
        }
    }
}
