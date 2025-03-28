import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeTaxCalculator extends JFrame {
    private JTextField incomeField, deductionField, taxCreditField;
    private JComboBox<String> filingStatusBox;
    private JLabel resultLabel;

    public IncomeTaxCalculator() {
        setTitle("Income Tax Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 248, 220)); // Light yellow
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Annual Income
        gbc.gridx = 0; gbc.gridy = 0;
        add(createLabel("Annual Income (₹):", labelFont), gbc);
        gbc.gridx = 1;
        incomeField = new JTextField(15);
        add(incomeField, gbc);

        // Deductions
        gbc.gridx = 0; gbc.gridy = 1;
        add(createLabel("Deductions (₹):", labelFont), gbc);
        gbc.gridx = 1;
        deductionField = new JTextField(15);
        add(deductionField, gbc);

        // Tax Credits
        gbc.gridx = 0; gbc.gridy = 2;
        add(createLabel("Tax Credits (₹):", labelFont), gbc);
        gbc.gridx = 1;
        taxCreditField = new JTextField(15);
        add(taxCreditField, gbc);

        // Filing Status
        gbc.gridx = 0; gbc.gridy = 3;
        add(createLabel("Filing Status:", labelFont), gbc);
        gbc.gridx = 1;
        String[] statuses = {"Individual", "Senior Citizen", "Super Senior Citizen"};
        filingStatusBox = new JComboBox<>(statuses);
        add(filingStatusBox, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 4;
        JButton calculateButton = createButton("Calculate", new Color(34, 167, 240));
        add(calculateButton, gbc);

        gbc.gridx = 1;
        JButton resetButton = createButton("Reset", new Color(255, 77, 77));
        add(resetButton, gbc);

        // Result Label
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        resultLabel = new JLabel("Estimated Tax: ₹0.00", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(139, 0, 0));
        add(resultLabel, gbc);

        // Action Listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
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

    private void calculateTax() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            double deductions = Double.parseDouble(deductionField.getText());
            double taxCredits = Double.parseDouble(taxCreditField.getText());

            // Apply deductions
            double taxableIncome = income - deductions;
            if (taxableIncome < 0) taxableIncome = 0;

            double tax = computeTax(taxableIncome);
            
            // Apply tax credits
            tax -= taxCredits;
            if (tax < 0) tax = 0;

            resultLabel.setText("Estimated Tax: ₹" + String.format("%.2f", tax));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    private double computeTax(double income) {
        double tax = 0;

        if (income > 1000000) {
            tax += (income - 1000000) * 0.30; // 30% tax for income above ₹10L
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 0.20; // 20% tax for ₹5L-10L
            income = 500000;
        }
        if (income > 250000) {
            tax += (income - 250000) * 0.05; // 5% tax for ₹2.5L-5L
        }
        return tax;
    }

    private void resetFields() {
        incomeField.setText("");
        deductionField.setText("");
        taxCreditField.setText("");
        filingStatusBox.setSelectedIndex(0);
        resultLabel.setText("Estimated Tax: ₹0.00");
    }

    public static void main(String[] args) {
        new IncomeTaxCalculator();
    }
}
