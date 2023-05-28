package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Booking;
import Classes.Ticket;

public class BookFlightGUI extends JFrame {
    private JTextField flightIdField;
    private JTextField ticketIdField;
    private JTextField numberOfSeatsField;
    private JTextField issueDateField;
    private JTextField ticketTypeField;
    private JTextField priceField;
    private JTextField ageField;
    private JTextField seatNumField;

    public BookFlightGUI() {
        setTitle("Book Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 2));

        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdField = new JTextField();

        JLabel ticketIdLabel = new JLabel("Ticket ID:");
        ticketIdField = new JTextField();

        JLabel numberOfSeatsLabel = new JLabel("Number of Seats:");
        numberOfSeatsField = new JTextField();

        JLabel issueDateLabel = new JLabel("Issue Date(yyyy-MM-dd):");
        issueDateField = new JTextField();

        JLabel ticketTypeLabel = new JLabel("Ticket Type(FirstClass-Business-Economy):");
        ticketTypeField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        JLabel seatNumLabel = new JLabel("Seat Number:");
        seatNumField = new JTextField();

        JButton bookButton = new JButton("Book");
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    int flightId = Integer.parseInt(flightIdField.getText());
                    String ticketId = ticketIdField.getText();
                    int numberOfSeats = Integer.parseInt(numberOfSeatsField.getText());
                    String issueDate = issueDateField.getText();
                    String ticketType = ticketTypeField.getText();
                    String price = priceField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    int seatNum = Integer.parseInt(seatNumField.getText());

                    // Perform the booking and ticket creation
                    Booking booking = new Booking(flightId, ticketId, numberOfSeats);
                    booking.addBooking();
                    Ticket ticket = new Ticket(issueDate, ticketType, price, age, seatNum, booking.getBookingId());
                    booking.addTicket(ticket);

                    // Show success message
                    JOptionPane.showMessageDialog(BookFlightGUI.this, "Booking successful!");
                    dispose();
                }
            }
        });

        mainPanel.add(flightIdLabel);
        mainPanel.add(flightIdField);
        mainPanel.add(ticketIdLabel);
        mainPanel.add(ticketIdField);
        mainPanel.add(numberOfSeatsLabel);
        mainPanel.add(numberOfSeatsField);
        mainPanel.add(issueDateLabel);
        mainPanel.add(issueDateField);
        mainPanel.add(ticketTypeLabel);
        mainPanel.add(ticketTypeField);
        mainPanel.add(priceLabel);
        mainPanel.add(priceField);
        mainPanel.add(ageLabel);
        mainPanel.add(ageField);
        mainPanel.add(seatNumLabel);
        mainPanel.add(seatNumField);
        mainPanel.add(bookButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private boolean validateInput() {
        if (flightIdField.getText().isEmpty() || ticketIdField.getText().isEmpty()
                || numberOfSeatsField.getText().isEmpty() || issueDateField.getText().isEmpty()
                || ticketTypeField.getText().isEmpty() || priceField.getText().isEmpty()
                || ageField.getText().isEmpty() || seatNumField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return false;
        }

        try {
            int flightId = Integer.parseInt(flightIdField.getText());
            int numberOfSeats = Integer.parseInt(numberOfSeatsField.getText());
            int age = Integer.parseInt(ageField.getText());
            int seatNum = Integer.parseInt(seatNumField.getText());

            if (flightId <= 0 || numberOfSeats <= 0 || age <= 0 || seatNum <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid input. Flight ID, number of seats, age, and seat number must be positive integers.");
                return false;
            }

            // Additional input validation if needed

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Flight ID, number of seats, age, and seat number must be integers.");
            return false;
        }

        return true;
    }
}