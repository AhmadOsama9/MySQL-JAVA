package GUI;

import Classes.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFlightGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField dateField;
    private JTextField departureTimeField;
    private JTextField arrivalTimeField;
    private JTextField arrivalAirportField;
    private JTextField departureAirportField;
    private JTextField airplaneIdField;
    private JTextField seatsField;
    private JTextField userIdField;
    private JTextField airportIdField;
    private JButton addButton;

    public AddFlightGUI() {
        setTitle("Add Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 2));

        JLabel dateLabel = new JLabel("Date (yyyy-MM-dd):");
        dateField = new JTextField();

        JLabel departureTimeLabel = new JLabel("Departure Time (HH:mm:ss):");
        departureTimeField = new JTextField();

        JLabel arrivalTimeLabel = new JLabel("Arrival Time (HH:mm:ss):");
        arrivalTimeField = new JTextField();

        JLabel arrivalAirportLabel = new JLabel("Arrival Airport:");
        arrivalAirportField = new JTextField();

        JLabel departureAirportLabel = new JLabel("Departure Airport:");
        departureAirportField = new JTextField();

        JLabel airplaneIdLabel = new JLabel("Airplane ID:");
        airplaneIdField = new JTextField();

        JLabel seatsLabel = new JLabel("No.Seats");
        seatsField = new JTextField();

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField();

        JLabel airportIdLabel = new JLabel("Airport ID:");
        airportIdField = new JTextField();

        addButton = new JButton("Add");
        addButton.addActionListener(this);

        mainPanel.add(dateLabel);
        mainPanel.add(dateField);
        mainPanel.add(departureTimeLabel);
        mainPanel.add(departureTimeField);
        mainPanel.add(arrivalTimeLabel);
        mainPanel.add(arrivalTimeField);
        mainPanel.add(arrivalAirportLabel);
        mainPanel.add(arrivalAirportField);
        mainPanel.add(departureAirportLabel);
        mainPanel.add(departureAirportField);
        mainPanel.add(airplaneIdLabel);
        mainPanel.add(airplaneIdField);
        mainPanel.add(seatsLabel);
        mainPanel.add(seatsField);
        mainPanel.add(userIdLabel);
        mainPanel.add(userIdField);
        mainPanel.add(airportIdLabel);
        mainPanel.add(airportIdField);
        mainPanel.add(addButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String dateString = dateField.getText().trim();
            String departureTime = departureTimeField.getText().trim();
            String arrivalTime = arrivalTimeField.getText().trim();
            String arrivalAirport = arrivalAirportField.getText().trim();
            String departureAirport = departureAirportField.getText().trim();
            String airplaneIdText = airplaneIdField.getText().trim();
            String userIdText = userIdField.getText().trim();
            String airportIdText = airportIdField.getText().trim();
            String NoSeats = seatsField.getText().trim();

            if (dateString.isEmpty() || departureTime.isEmpty() || arrivalTime.isEmpty()
                    || arrivalAirport.isEmpty() || departureAirport.isEmpty() || airplaneIdText.isEmpty()
                    || userIdText.isEmpty() || airportIdText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date selectedDate = dateFormat.parse(dateString);
                int airplaneId = Integer.parseInt(airplaneIdText);
                int userId = Integer.parseInt(userIdText);
                int airportId = Integer.parseInt(airportIdText);
                int seats = Integer.parseInt(NoSeats);

                Flight flight = new Flight(dateString, seats, arrivalTime, departureTime, arrivalAirport,
                        departureAirport, airplaneId, userId, airportId);
                flight.addFlight();
                JOptionPane.showMessageDialog(this, "Flight added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (java.text.ParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
