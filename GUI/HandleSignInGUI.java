package GUI;

import Classes.Admin;
import Classes.Passenger;
import Classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;

public class HandleSignInGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton;

    private JLabel errorLabel;

    private User user;
    private AdminMenuGUI adminMenuGUI;
    private Admin admin;
    private PassengerMenuGUI passengerMenuGUI;

    public HandleSignInGUI() {
        setTitle("Sign In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(250, 30));

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(250, 30));

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 15));
        signInButton.setPreferredSize(new Dimension(100, 40));


        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    showError("Please enter both email and password");
                } else {
                    user = new User();
                    user = user.getUserByEmail(email);

                    if (user != null) {
                        String userPassword = user.getPassword();

                        if (userPassword.equals(password)) {
                            String userRole = user.getUserRole();

                            if (userRole.equalsIgnoreCase("admin")) {
                                System.out.println("You are a valid admin");
                                Admin admin = new Admin(user.getName(), user.getPhone(), user.getEmail(),
                                        user.getPassword(), user.getUserRole(), user.getAge(), user.getPayment(),
                                        user.getAddress());
                                adminMenuGUI = new AdminMenuGUI(admin);

                            } else if (userRole.equalsIgnoreCase("passenger")) {
                                System.out.println("You are a valid passenger");
                                Passenger passenger = new Passenger(user.getName(), user.getPhone(), user.getEmail(),
                                        user.getPassword(), user.getUserRole(), user.getAge(), user.getPayment(),
                                        user.getAddress());
                                passengerMenuGUI = new PassengerMenuGUI(passenger);
                            }
                        } else {
                            showError("Wrong password");
                        }
                    } else {
                        showError("Cannot find the user in the database");
                    }
                }
            }
        });
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(emailLabel, constraints);

        constraints.gridx = 1;
        mainPanel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        mainPanel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        mainPanel.add(signInButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        mainPanel.add(errorLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.weighty = 1.0;
        JLabel spacerLabel = new JLabel();
        mainPanel.add(spacerLabel, constraints);

        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void showError(String errorMessage) {
        errorLabel.setText("Error: " + errorMessage);
    }
}
