import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvestmentCalculatorGUI extends JFrame {
    private JTextField principalField, rateField, yearsField;
    private JLabel resultLabel;

    public InvestmentCalculatorGUI() {
        setTitle("Investment Return Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(230, 240, 255)); // Light blue background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Initial Investment
        gbc.gridx = 0; gbc.gridy = 0;
        add(createLabel("Initial Investment (₹):", labelFont), gbc);
        gbc.gridx = 1;
        principalField = new JTextField(15);
        add(principalField, gbc);

        // Annual Interest Rate
        gbc.gridx = 0; gbc.gridy = 1;
        add(createLabel("Annual Return Rate (%):", labelFont), gbc);
        gbc.gridx = 1;
        rateField = new JTextField(15);
        add(rateField, gbc);

        // Investment Years
        gbc.gridx = 0; gbc.gridy = 2;
        add(createLabel("Investment Duration (Years):", labelFont), gbc);
        gbc.gridx = 1;
        yearsField = new JTextField(15);
        add(yearsField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        JButton calculateButton = createButton("Calculate", new Color(34, 167, 240));
        add(calculateButton, gbc);

        gbc.gridx = 1;
        JButton resetButton = createButton("Reset", new Color(255, 77, 77));
        add(resetButton, gbc);

        // Result Label
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        resultLabel = new JLabel("Future Investment Value: ₹0.00", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 100, 0));
        add(resultLabel, gbc);

        // Action Listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateInvestment();
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

    private void calculateInvestment() {
        try {
            double principal = Double.parseDouble(principalField.getText());
            double annualRate = Double.parseDouble(rateField.getText()) / 100;
            int years = Integer.parseInt(yearsField.getText());

            // Future Value Calculation
            double futureValue = principal * Math.pow(1 + annualRate, years);

            resultLabel.setText("Future Value: ₹" + String.format("%.2f", futureValue));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    private void resetFields() {
        principalField.setText("");
        rateField.setText("");
        yearsField.setText("");
        resultLabel.setText("Future Investment Value: ₹0.00");
    }

    public static void main(String[] args) {
        new InvestmentCalculatorGUI();
    }
}
