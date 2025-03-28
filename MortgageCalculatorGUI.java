import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageCalculatorGUI extends JFrame {
    private JTextField loanAmountField, interestRateField, loanTermField;
    private JLabel resultLabel;

    public MortgageCalculatorGUI() {
        setTitle("Mortgage Calculator (₹)");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(230, 240, 255)); // Light blue background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Loan Amount
        gbc.gridx = 0; gbc.gridy = 0;
        add(createLabel("Loan Amount (₹):", labelFont), gbc);
        gbc.gridx = 1;
        loanAmountField = new JTextField(15);
        add(loanAmountField, gbc);

        // Interest Rate
        gbc.gridx = 0; gbc.gridy = 1;
        add(createLabel("Annual Interest Rate (%):", labelFont), gbc);
        gbc.gridx = 1;
        interestRateField = new JTextField(15);
        add(interestRateField, gbc);

        // Loan Term
        gbc.gridx = 0; gbc.gridy = 2;
        add(createLabel("Loan Term (Years):", labelFont), gbc);
        gbc.gridx = 1;
        loanTermField = new JTextField(15);
        add(loanTermField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        JButton calculateButton = createButton("Calculate", new Color(34, 167, 240));
        add(calculateButton, gbc);

        gbc.gridx = 1;
        JButton resetButton = createButton("Reset", new Color(255, 77, 77));
        add(resetButton, gbc);

        // Result Label
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        resultLabel = new JLabel("Monthly Payment: ₹0.00", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 100, 0));
        add(resultLabel, gbc);

        // Action Listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMortgage();
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

    private void calculateMortgage() {
        try {
            double principal = Double.parseDouble(loanAmountField.getText());
            double annualInterestRate = Double.parseDouble(interestRateField.getText());
            int loanTermYears = Integer.parseInt(loanTermField.getText());

            double monthlyInterestRate = (annualInterestRate / 100) / 12;
            int numberOfPayments = loanTermYears * 12;

            double monthlyPayment = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                    (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

            resultLabel.setText("Monthly Payment: ₹" + String.format("%.2f", monthlyPayment));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    private void resetFields() {
        loanAmountField.setText("");
        interestRateField.setText("");
        loanTermField.setText("");
        resultLabel.setText("Monthly Payment: ₹0.00");
    }

    public static void main(String[] args) {
        new MortgageCalculatorGUI();
    }
}
