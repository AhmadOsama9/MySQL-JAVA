package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpAndSignInGUI extends JFrame {
    private JPanel mainPanel;
    private JButton signUpButton;
    private JButton signInButton;
    private HandleSignUpGUI handleSignUpGUI;
    private HandleSignInGUI handleSignInGUI;

    public SignUpAndSignInGUI() {
        setTitle("Sign Up and Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null); // Center the frame on the screen

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        signUpButton = createMenuButton("Sign Up", new Color(52, 152, 219), Color.WHITE);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSignUpGUI = new HandleSignUpGUI();
            }
        });
        mainPanel.add(signUpButton);

        signInButton = createMenuButton("Sign In", new Color(46, 204, 113), Color.WHITE);
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSignInGUI = new HandleSignInGUI();
            }
        });
        mainPanel.add(signInButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createMenuButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 60));
        button.setMaximumSize(button.getPreferredSize());
        return button;
    }
}
