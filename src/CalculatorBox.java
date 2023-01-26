import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CalculatorBox extends JFrame{

    JButton bPlus, bMinus, bDiv, bMulti, bClear, bAC, bEquals, bDot, bSquare;
    JButton[] numB;
    JTextField output;
    String prev, cur, op;

    public void processOutputNumber(){
        if(cur.length() > 1){
            String integerPart = cur.split("\\.")[0];
            String decimalPart = cur.split("\\.")[1];
            if(decimalPart.equals("0")){
                cur = integerPart;
            }
        }
    }
    public void del(){
        if(cur.length() > 0){
            cur = cur.substring(0, cur.length() - 1);
        }
    }
    public void clear(){
        cur = "";
        prev = "";
        op = null;
    }
    public void updateOutput(){
        output.setText(cur);
    }
    public void appendToOutput(String num){
        if(num.equals(".") && cur.contains(".")){
            return;
        }
        cur += num;
    }
    public void selectOp(String newOp){
        if(cur.isEmpty()){
            op = newOp;
            return;
        }
        if(!prev.isEmpty()){
            calculate();
        }
        op = newOp;
        prev = cur;
        cur = "";
    }
    public void calculate(){
        if(prev.length() < 1 || cur.length() < 1){
            return;
        }
        double res = 0.0;
        double num1 = Double.parseDouble(prev);
        double num2 = Double.parseDouble(cur);
        switch (op) {
            case "+" -> res = num1 + num2;
            case "-" -> res = num1 - num2;
            case "*" -> res = num1 * num2;
            case "/" -> res = num1 / num2;
            default -> {
            }
        }
        cur = String.valueOf(res);
        op = null;
        prev = "";
        processOutputNumber();
    }

    public void square(){
        double num1 = Double.parseDouble(cur);
        double num3 = (int)(num1 * num1);
        cur = String.valueOf(num3);
        op = null;
        prev = "";
        processOutputNumber();
    }

    private class NumBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            for(JButton btn: numB){
                if(selectedBtn == btn){
                    appendToOutput(btn.getText());
                    updateOutput();
                }
            }
        }
    }

    private class OpBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            if(selectedBtn == bPlus){
                selectOp(bPlus.getText());
            }
            if(selectedBtn == bMinus){
                selectOp(bMinus.getText());
            }
            if(selectedBtn == bDiv){
                selectOp(bDiv.getText());
            }
            if(selectedBtn == bMulti){
                selectOp(bMulti.getText());
            }
            updateOutput();
        }
    }
    private class OtherBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            if(selectedBtn == bClear){
                del();
            }
            else if(selectedBtn == bAC){
                clear();
            }
            else if(selectedBtn == bEquals){
                calculate();
            }
            else if(selectedBtn == bSquare){
                square();
            }
            updateOutput();
        }
    }

    public CalculatorBox() {
        super("Calculator");
        JPanel mainPanel = new JPanel();
        JFrame frame2 = new JFrame();
        JPanel panel2 = new JPanel();
        mainPanel.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        panel2.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        mainPanel.setMaximumSize(new Dimension(295,405));
        panel2.setMaximumSize(new Dimension(295,405));
        mainPanel.setPreferredSize(new Dimension(295,405));
        panel2.setMaximumSize(new Dimension(295,405));

        cur = "";
        prev = "";

        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();
        JPanel row5 = new JPanel();

        output = new JTextField(10);
        bPlus = new JButton("+");
        bMinus = new JButton("-");
        bMulti = new JButton("*");
        bDiv = new JButton("/");
        bDot = new JButton(".");
        bEquals = new JButton("=");
        bClear = new JButton("<-");
        bAC = new JButton("AC");
        bSquare = new JButton("x^2");

        NumBtnHandler numBtnHandler = new NumBtnHandler();
        OpBtnHandler opBtnHandler = new OpBtnHandler();
        OtherBtnHandler otherBtnHandler = new OtherBtnHandler();

        numB = new JButton[11];
        numB[10] = bDot;
        for(int count = 0; count < numB.length - 1; count++){
            numB[count] = new JButton(String.valueOf(count));
            numB[count].setFont(new Font("JetBrains Mono", Font.BOLD, 16));
            numB[count].addActionListener(numBtnHandler);
        }
        bDot.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bAC.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bEquals.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bPlus.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bMulti.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bMinus.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bDiv.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bClear.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        bSquare.setFont(new Font("JetBrains Mono", Font.BOLD, 16));


        panel2.setMaximumSize(new Dimension(295,405));
        output.setMaximumSize(new Dimension(25,45));
        panel2.setPreferredSize(new Dimension(295,405));
        output.setPreferredSize(new Dimension(25,45));
        panel2.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        output.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        output.setDisabledTextColor(new Color(0,0,0));
        output.setMargin(new Insets(5,5,5,5));
        output.setText("0");

        bDot.addActionListener(numBtnHandler);

        bClear.addActionListener(otherBtnHandler);
        bAC.addActionListener(otherBtnHandler);
        bEquals.addActionListener(otherBtnHandler);
        bSquare.addActionListener(otherBtnHandler);

        bPlus.addActionListener(opBtnHandler);
        bMinus.addActionListener(opBtnHandler);
        bMulti.addActionListener(opBtnHandler);
        bDiv.addActionListener(opBtnHandler);

        row1.setLayout(new BoxLayout(row1,BoxLayout.LINE_AXIS));
        row1.setBackground(new Color(255,251,244));
        row2.setLayout(new BoxLayout(row2,BoxLayout.LINE_AXIS));
        row2.setBackground(new Color(255,251,244));
        row3.setLayout(new BoxLayout(row3,BoxLayout.LINE_AXIS));
        row3.setBackground(new Color(255,251,244));
        row4.setLayout(new BoxLayout(row4,BoxLayout.LINE_AXIS));
        row4.setBackground(new Color(255,251,244));
        row5.setLayout(new BoxLayout(row5,BoxLayout.LINE_AXIS));
        row5.setBackground(new Color(255,251,244));

        row1.add(Box.createHorizontalGlue());
        row1.add(bAC);
        row1.add(bClear);
        row1.add(bPlus);
        row2.add(numB[7]);
        row2.add(numB[8]);
        row2.add(numB[9]);
        row2.add(bMinus);
        row3.add(numB[4]);
        row3.add(numB[5]);
        row3.add(numB[6]);
        row3.add(bMulti);
        row4.add(numB[1]);
        row4.add(numB[2]);
        row4.add(numB[3]);
        row4.add(bDiv);
        row5.add(bSquare);
        row5.add(numB[0]);
        row5.add(bDot);
        row5.add(bEquals);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        panel2.add(output, BorderLayout.CENTER);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        panel2.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row4);
        mainPanel.add(row5);

        this.add(mainPanel);
        frame2.add(panel2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        frame2.setVisible(true);
        this.setSize(300,200);
        frame2.setSize(200,130);
        mainPanel.setBackground(new Color(255,251,244));
        panel2.setBackground(new Color(255,251,244));
        this.setBackground(new Color(255,251,244));
        frame2.setBackground(new Color(255,251,244));
    }

    public static void main(String[] args) {
        new CalculatorBox();
    }
}