package calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    private final Color rosePastel = new Color(255, 204, 204);

    public CalculatorUI() {
        frame = new JFrame("Calculatrice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setEditable(false);
        textField.setBackground(rosePastel);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        functionButtons = new JButton[]{addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton};
        for (JButton button : functionButtons) {
            button.addActionListener(new FunctionButtonListener());
            button.setBackground(rosePastel);
        }

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
            numberButtons[i].setBackground(rosePastel);
        }

        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);
        delButton.setBackground(rosePastel);
        clrButton.setBackground(rosePastel);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(rosePastel);

        for (int i = 1; i < 10; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(addButton);
        panel.add(numberButtons[0]);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String value = ((JButton) e.getSource()).getText();
            textField.setText(textField.getText() + value);
        }
    }

    private class FunctionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String value = ((JButton) e.getSource()).getText();
            if (!value.equals("=")) {
                if (!textField.getText().isEmpty()) {
                    try {
                        num1 = Double.parseDouble(textField.getText());
                        operator = value.charAt(0);
                        textField.setText("");
                    } catch (NumberFormatException ex) {
                        textField.setText("Erreur");
                    }
                } else if (result != 0) {
                    num1 = result;
                    operator = value.charAt(0);
                }
            } else {
                if (!textField.getText().isEmpty()) {
                    num2 = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case '+':
                            result = Calculator.add(num1, num2);
                            break;
                        case '-':
                            result = Calculator.subtract(num1, num2);
                            break;
                        case '*':
                            result = Calculator.multiply(num1, num2);
                            break;
                        case '/':
                            result = Calculator.divide(num1, num2);
                            break;
                    }
                    textField.setText(String.valueOf(result));
                    num1 = result;
                }
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorUI();
    }
}
