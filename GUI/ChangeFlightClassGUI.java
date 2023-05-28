package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Booking;

public class ChangeFlightClassGUI extends JFrame {
    private JTextField bookingIdField;
    private JTextField classTypeField;
    private JTextArea resultTextArea;

    public ChangeFlightClassGUI() {
        setTitle("Change Flight Class");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel bookingIdLabel = new JLabel("Booking ID:");
        bookingIdField = new JTextField();

        JLabel classTypeLabel = new JLabel("New Class Type:");
        classTypeField = new JTextField();

        inputPanel.add(bookingIdLabel);
        inputPanel.add(bookingIdField);
        inputPanel.add(classTypeLabel);
        inputPanel.add(classTypeField);

        JButton changeButton = new JButton("Change Flight Class");
        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    int bookingId = Integer.parseInt(bookingIdField.getText());
                    String classType = classTypeField.getText();

                    Booking booking = new Booking();
                    String changeResult = booking.changeTicketType(classType, bookingId);

                    resultTextArea.setText(changeResult);
                }
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(changeButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private boolean validateInput() {
        if (bookingIdField.getText().isEmpty() || classTypeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the booking ID and new class type.");
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