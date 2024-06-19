package LockerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApp extends JFrame {
    private static final int GRID_SIZE = 3;
    private static final int BUTTON_COUNT = 10;

    private JPasswordField passwordField;
    private JButton[] numberButtons;
    private JButton clearButton, enterButton;
    private JLabel statusLabel;

    private String savedPassword = null;

    public LockerApp() {
        setTitle("Lock Class");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        passwordField = new JPasswordField(20);
        passwordField.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE + 1));

        numberButtons = new JButton[BUTTON_COUNT];
        for (int i = 1; i < BUTTON_COUNT; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
            buttonPanel.add(numberButtons[i]);
        }

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> passwordField.setText(""));
        buttonPanel.add(clearButton);

        numberButtons[0] = new JButton("0");
        numberButtons[0].addActionListener(new NumberButtonListener());
        buttonPanel.add(numberButtons[0]);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        buttonPanel.add(enterButton);

        statusLabel = new JLabel("Enter Password", SwingConstants.CENTER);

        add(buttonPanel, BorderLayout.CENTER);
        add(passwordField, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = passwordField.getText() + ((JButton) e.getSource()).getText();
            passwordField.setText(text);
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = new String(passwordField.getPassword());
            if (savedPassword == null) {
                savedPassword = enteredPassword;
                statusLabel.setText("Password Set");
            } else if (savedPassword.equals(enteredPassword)) {
                statusLabel.setText("Correct Password");
            } else {
                statusLabel.setText("Incorrect Password");
            }
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LockerApp lockerApp = new LockerApp();
            lockerApp.setVisible(true);
        });
    }
}
