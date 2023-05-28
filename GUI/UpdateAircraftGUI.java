package GUI;
import Classes.Aircraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAircraftGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField modelField;
    private JTextField seatsField;
    private JTextField statusField;
    private JButton updateButton;

    public UpdateAircraftGUI() {
        setTitle("Update Aircraft");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("Aircraft ID:");
        idField = new JTextField();

        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField();

        JLabel seatsLabel = new JLabel("Number of Seats:");
        seatsField = new JTextField();

        JLabel statusLabel = new JLabel("Airplane Status:");
        statusField = new JTextField();

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(modelLabel);
        mainPanel.add(modelField);
        mainPanel.add(seatsLabel);
        mainPanel.add(seatsField);
        mainPanel.add(statusLabel);
        mainPanel.add(statusField);
        mainPanel.add(updateButton);

        setContentPane(mainPanel);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String idText = idField.getText().trim();
            String model = modelField.getText().trim();
            String seatsText = seatsField.getText().trim();
            String airplaneStatus = statusField.getText().trim();


            if (idText.isEmpty() || model.isEmpty() || seatsText.isEmpty() || airplaneStatus.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                int numberOfSeats = Integer.parseInt(seatsText);

                Aircraft aircraft = new Aircraft();
                Aircraft aircraftById = aircraft.getAircraftById(id);

                if (aircraftById == null) {
                    JOptionPane.showMessageDialog(this, "Aircraft not found.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    aircraftById.setModel(model);
                    aircraftById.setNumberOfSeats(numberOfSeats);
                    aircraftById.setAirplaneStatus(airplaneStatus);
                    aircraftById.updateAircraft();
                    JOptionPane.showMessageDialog(this, "Aircraft updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID or number of seats.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
