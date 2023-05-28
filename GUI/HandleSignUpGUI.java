package GUI;

import Classes.Address;
import Classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleSignUpGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JTextField userRoleField;
    private JTextField paymentField;
    private JTextField ageField;
    private JTextField countryField;
    private JTextField cityField;
    private JTextField streetField;

    private JLabel nameLabelError;
    private JLabel emailLabelError;
    private JLabel passwordLabelError;
    private JLabel phoneLabelError;
    private JLabel userRoleLabelError;
    private JLabel ageLabelError;

    private JButton signUpButton;

    private User user;

    public HandleSignUpGUI() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the window on the screen

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(13, 3));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(nameField);

        nameLabelError = new JLabel("");
        nameLabelError.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(nameLabelError);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(emailField);

        emailLabelError = new JLabel("");
        emailLabelError.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(emailLabelError);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(passwordField);

        passwordLabelError = new JLabel("");
        passwordLabelError.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(passwordLabelError);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(phoneField);

        phoneLabelError = new JLabel("");
        phoneLabelError.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(phoneLabelError);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(ageLabel);

        ageField = new JTextField();
        ageField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(ageField);

        ageLabelError = new JLabel("");
        ageLabelError.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(ageLabelError);

        JLabel userRoleLabel = new JLabel("User Role:");
        userRoleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(userRoleLabel);

        userRoleField = new JTextField();
        userRoleField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(userRoleField);

        userRoleLabelError = new JLabel("");
        userRoleLabelError.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(userRoleLabelError);

        JLabel paymentLabel = new JLabel("Payment: (if admin enter (empty))");
        paymentLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(paymentLabel);

        paymentField = new JTextField();
        paymentField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(paymentField);

        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(countryLabel);

        countryField = new JTextField();
        countryField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(countryField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(cityLabel);

        cityField = new JTextField();
        cityField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(cityField);

        JLabel streetLabel = new JLabel("Street:");
        streetLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(streetLabel);

        streetField = new JTextField();
        streetField.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(streetField);

        JLabel emptyLabel1 = new JLabel("");
        mainPanel.add(emptyLabel1);

        JLabel emptyLabel2 = new JLabel("");
        mainPanel.add(emptyLabel2);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 15));
        mainPanel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String phone = phoneField.getText();
                String userRole = userRoleField.getText();
                String payment = paymentField.getText();
                String age = ageField.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String country = countryField.getText();

                boolean valid = true;

                nameLabelError.setText("");
                emailLabelError.setText("");
                passwordLabelError.setText("");
                phoneLabelError.setText("");
                userRoleLabelError.setText("");
                ageLabelError.setText("");

                if (name.isEmpty()) {
                    nameLabelError.setText("<html><font color='red'>Error: Name is required</font></html>");
                    valid = false;
                }

                if (email.isEmpty()) {
                    emailLabelError.setText("<html><font color='red'>Error: Email is required</font></html>");
                    valid = false;
                } else if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                    emailLabelError.setText("<html><font color='red'>Error: Invalid email address</font></html>");
                    valid = false;
                }

                if (password.isEmpty()) {
                    passwordLabelError.setText("<html><font color='red'>Error: Password is required</font></html>");
                    valid = false;
                } else if (password.length() < 8) {
                    passwordLabelError.setText("<html><font color='red'>Error: Password must be at least 8 characters long</font></html>");
                    valid = false;
                }

                if (phone.isEmpty()) {
                    phoneLabelError.setText("<html><font color='red'>Error: Phone is required</font></html>");
                    valid = false;
                } else if (!phone.matches("^(010|011|012|015)\\d{8}$")) {
                    phoneLabelError.setText("<html><font color='red'>Error: Invalid phone number</font></html>");
                    valid = false;
                }

                if (age.isEmpty()) {
                    ageLabelError.setText("<html><font color='red'>Error: Age is required</font></html>");
                    valid = false;
                } else if (Integer.parseInt(age) < 16) {
                    ageLabelError.setText("<html><font color='red'>Error: Invalid age</font></html>");
                    valid = false;
                }

                if (userRole.isEmpty()) {
                    userRoleLabelError.setText("<html><font color='red'>Error: User Role is required</font></html>");
                    valid = false;
                } else if (!userRole.equalsIgnoreCase("Admin") && !userRole.equalsIgnoreCase("Passenger")) {
                    userRoleLabelError.setText("<html><font color='red'>Error: Invalid role</font></html>");
                    valid = false;
                }

                if (valid) {
                    Address address = new Address(country, city, street);
                    user = new User(name, phone, email, password, userRole, Integer.parseInt(age), payment, address);
                    user.addUser();
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                }
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
    }
}
