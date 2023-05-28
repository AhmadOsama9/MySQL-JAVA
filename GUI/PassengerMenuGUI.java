package GUI;

import Classes.Passenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerMenuGUI extends JFrame {
    private Passenger passenger;
    private FlightSearchGUI flightSearchGUI;

    public PassengerMenuGUI(Passenger passenger) {
        this.passenger = passenger;

        setTitle("Passenger Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton bookFlightButton = createMenuButton("Book Flight");
        bookFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookFlightGUI bookFlightGUI = new BookFlightGUI();
            }
        });
        mainPanel.add(bookFlightButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton cancelBookingButton = createMenuButton("Cancel Booking");
        cancelBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CancelBookingGUI cancelBookingGUI = new CancelBookingGUI();
            }
        });
        mainPanel.add(cancelBookingButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton changeFlightClassButton = createMenuButton("Change Your Flight Class");
        changeFlightClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangeFlightClassGUI changeFlightClassGUI = new ChangeFlightClassGUI();
            }
        });
        mainPanel.add(changeFlightClassButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton viewBookingInfoButton = createMenuButton("View Booking Info");
        viewBookingInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBookingGUI viewBookingGui = new ViewBookingGUI();
            }
        });
        mainPanel.add(viewBookingInfoButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton searchFlightsButton = createMenuButton("Search Flights");
        searchFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flightSearchGUI = new FlightSearchGUI();
            }
        });
        mainPanel.add(searchFlightsButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing

        JButton exitButton = createMenuButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(exitButton);

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
