import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    private TextField display;
    private Panel panel;
    private String operator;
    private double operand1, operand2, result;
    private boolean startNewNumber = true;

    public Calculator() {
        setLayout(new BorderLayout());
        setSize(250, 350);
        setTitle("Basic Calculator");

        display = new TextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        panel = new Panel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        int row = 0, col = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPreferredSize(new Dimension(40, 40));
            button.addActionListener(this);

            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(button, gbc);

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
        add(panel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else {
            if (command.equals("=")) {
                operand2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        try {
                            result = operand1 / operand2;
                        } catch (ArithmeticException ex) {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                startNewNumber = true;
            } else {
                operator = command;
                operand1 = Double.parseDouble(display.getText());
                startNewNumber = true;
            }
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}
