package GUI;
import Classes.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelBookingGUI extends JFrame {
    private JTextField bookingIdField;
    private JTextArea resultTextArea;

    public CancelBookingGUI() {
        setTitle("Cancel Booking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel bookingIdLabel = new JLabel("Booking ID:");
        bookingIdField = new JTextField();

        inputPanel.add(bookingIdLabel);
        inputPanel.add(bookingIdField);

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    int bookingId = Integer.parseInt(bookingIdField.getText());

                    Booking booking = new Booking();
                    String cancellationResult = booking.removeBooking(bookingId);

                    resultTextArea.setText(cancellationResult);
                }
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(cancelButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private boolean validateInput() {
        if (bookingIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the booking ID.");
            return false;
        }

        try {
            int bookingId = Integer.parseInt(bookingIdField.getText());

            if (bookingId <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid input. Booking ID must be a positive integer.");
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Booking ID must be an integer.");
            return false;
        }

        return true;
    }
}