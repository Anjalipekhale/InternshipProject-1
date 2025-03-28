import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingsGoalCalculatorGUI extends JFrame {
    private JTextField goalField, rateField, yearsField;
    private JLabel resultLabel;

    public SavingsGoalCalculatorGUI() {
        setTitle("Savings Goal Calculator");
        setSize(480, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // Light Blue
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Savings Goal
        gbc.gridx = 0; gbc.gridy = 0;
        add(createLabel("Savings Goal (₹):", labelFont), gbc);
        gbc.gridx = 1;
        goalField = new JTextField(15);
        add(goalField, gbc);

        // Annual Interest Rate
        gbc.gridx = 0; gbc.gridy = 1;
        add(createLabel("Expected Return Rate (%):", labelFont), gbc);
        gbc.gridx = 1;
        rateField = new JTextField(15);
        add(rateField, gbc);

        // Time Frame (Years)
        gbc.gridx = 0; gbc.gridy = 2;
        add(createLabel("Time Frame (Years):", labelFont), gbc);
        gbc.gridx = 1;
        yearsField = new JTextField(15);
        add(yearsField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        JButton calculateButton = createButton("Calculate", new Color(46, 204, 113));
        add(calculateButton, gbc);

        gbc.gridx = 1;
        JButton resetButton = createButton("Reset", new Color(231, 76, 60));
        add(resetButton, gbc);

        // Result Label
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        resultLabel = new JLabel("Monthly Savings Needed: ₹0.00", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 100, 0));
        add(resultLabel, gbc);

        // Action Listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSavings();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        return button;
    }

    private void calculateSavings() {
        try {
            double goalAmount = Double.parseDouble(goalField.getText());
            double annualRate = Double.parseDouble(rateField.getText()) / 100;
            int years = Integer.parseInt(yearsField.getText());

            double monthlyRate = annualRate / 12;
            int months = years * 12;

            // Calculate Monthly Savings Required
            double monthlySavings = (goalAmount * monthlyRate) / (Math.pow(1 + monthlyRate, months) - 1);

            resultLabel.setText("Monthly Savings Needed: ₹" + String.format("%.2f", monthlySavings));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    private void resetFields() {
        goalField.setText("");
        rateField.setText("");
        yearsField.setText("");
        resultLabel.setText("Monthly Savings Needed: ₹0.00");
    }

    public static void main(String[] args) {
        new SavingsGoalCalculatorGUI();
    }
}
