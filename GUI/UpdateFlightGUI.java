package GUI;

import Classes.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFlightGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField departureTimeField;
    private JTextField arrivalTimeField;
    private JButton updateButton;

    public UpdateFlightGUI() {
        setTitle("Update Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("Flight ID:");
        idField = new JTextField();

        JLabel departureTimeLabel = new JLabel("Departure Time(HH:mm:ss):");
        departureTimeField = new JTextField();

        JLabel arrivalTimeLabel = new JLabel("Arrival Time(HH:mm:ss):");
        arrivalTimeField = new JTextField();

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(departureTimeLabel);
        mainPanel.add(departureTimeField);
        mainPanel.add(arrivalTimeLabel);
        mainPanel.add(arrivalTimeField);
        mainPanel.add(updateButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            int id;
            String departureTime;
            String arrivalTime;

            try {
                id = Integer.parseInt(idField.getText());
                departureTime = departureTimeField.getText().trim();
                arrivalTime = arrivalTimeField.getText().trim();

                if (departureTime.isEmpty() || arrivalTime.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Flight flight = new Flight();
                Flight flightById = flight.getFlightById(id);

                if (flightById == null) {
                    JOptionPane.showMessageDialog(this, "Flight not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                flightById.setDepartureTime(departureTime);
                flightById.setArrivalTime(arrivalTime);
                flightById.updateFlight();

                JOptionPane.showMessageDialog(this, "Flight updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}