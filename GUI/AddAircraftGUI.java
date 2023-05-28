package GUI;
import Classes.Aircraft;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAircraftGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField modelField;
    private JTextField seatsField;
    private JTextField statusField;
    private JButton addButton;

    public AddAircraftGUI() {
        setTitle("Add Aircraft");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));

        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField();

        JLabel seatsLabel = new JLabel("Number of Seats:");
        seatsField = new JTextField();

        JLabel statusLabel = new JLabel("Airplane Status:");
        statusField = new JTextField();

        addButton = new JButton("Add");
        addButton.addActionListener(this);

        mainPanel.add(modelLabel);
        mainPanel.add(modelField);
        mainPanel.add(seatsLabel);
        mainPanel.add(seatsField);
        mainPanel.add(statusLabel);
        mainPanel.add(statusField);
        mainPanel.add(addButton);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String model = modelField.getText().trim();
            String seatsText = seatsField.getText().trim();
            String airplaneStatus = statusField.getText().trim();

            if (model.isEmpty() || seatsText.isEmpty() || airplaneStatus.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int numberOfSeats = Integer.parseInt(seatsText);

                Aircraft aircraft = new Aircraft(model, numberOfSeats, airplaneStatus, 2);
                aircraft.addAircraft();
                JOptionPane.showMessageDialog(this, "Aircraft added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the GUI after successful addition
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number of seats.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
