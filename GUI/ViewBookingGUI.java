package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Booking;

public class ViewBookingGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel bookingIdLabel;
    private JTextField bookingIdField;
    private JButton viewButton;
    private JTextArea resultTextArea;

    public ViewBookingGUI() {
        frame = new JFrame("View Bookings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        bookingIdLabel = new JLabel("Enter booking ID: ");
        bookingIdField = new JTextField(10);
        viewButton = new JButton("View");
        resultTextArea = new JTextArea(10, 20);
        resultTextArea.setEditable(false);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bookingId = Integer.parseInt(bookingIdField.getText());

                Booking booking = new Booking();
                booking = booking.getBookingById(bookingId);
                if (booking != null) {
                    String bookingDetails = "Booking ID: " + booking.getBookingId() + "\n"
                            + "Booking Flight ID: " + booking.getFlightId() + "\n";
                    resultTextArea.setText(bookingDetails);
                } else {
                    resultTextArea.setText("No bookings found for the passenger.");
                }
            }
        });

        panel.add(bookingIdLabel);
        panel.add(bookingIdField);
        panel.add(viewButton);
        panel.add(resultTextArea);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

}
