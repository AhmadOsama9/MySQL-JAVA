package GUI;

import Classes.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuGUI extends JFrame {
    private Admin admin;
    private FlightSearchGUI flightSearchGUI;

    public AdminMenuGUI(Admin admin) {
        this.admin = admin;

        setTitle("Admin Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton updateAircraftButton = createMenuButton("Update Aircraft");
        updateAircraftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateAircraftGUI updateAircraftGUI = new UpdateAircraftGUI();
            }
        });
        mainPanel.add(updateAircraftButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton addAircraftButton = createMenuButton("Add Aircraft");
        addAircraftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddAircraftGUI addAircraftGUI = new AddAircraftGUI();
            }
        });
        mainPanel.add(addAircraftButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton addFlightButton = createMenuButton("Add Flight");
        addFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddFlightGUI addFlightGUI = new AddFlightGUI();
            }
        });
        mainPanel.add(addFlightButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton updateFlightButton = createMenuButton("Update Flight");
        updateFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateFlightGUI updateFlightGUI = new UpdateFlightGUI();
            }
        });
        mainPanel.add(updateFlightButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton showFlightsButton = createMenuButton("Show Flights");
        showFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flightSearchGUI = new FlightSearchGUI();
            }
        });
        mainPanel.add(showFlightsButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 23));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(59, 89, 152));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
