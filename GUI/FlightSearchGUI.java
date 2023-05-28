package GUI;

import ConnectToDatabase.ConnectToDatabase;
import Classes.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchGUI extends JFrame {
    private List<Flight> allFlights;
    private List<Flight> filteredFlights;

    private Connection conn;

    private JButton searchButton;
    private JTextArea flightsTextArea;
    private JPanel resultPanel;

    public FlightSearchGUI() {
        conn = ConnectToDatabase.openConnection();
        allFlights = new ArrayList<>();
        filteredFlights = new ArrayList<>();

        setTitle("Flight Search");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new GridLayout(4, 1));

        JCheckBox arrivalDateCheckBox = new JCheckBox("Arrival Time(HH:mm:ss):");
        filtersPanel.add(arrivalDateCheckBox);

        JCheckBox departureDateCheckBox = new JCheckBox("Departure Time(HH:mm:ss):");
        filtersPanel.add(departureDateCheckBox);

        JCheckBox requiredSeatsCheckBox = new JCheckBox("Required Number of Seats");
        filtersPanel.add(requiredSeatsCheckBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filteredFlights.clear();

                int requiredSeats = 0;
                if (requiredSeatsCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(FlightSearchGUI.this, "The search will include flights with more available seats than the specified number.");
                    String seatsInput = JOptionPane.showInputDialog("Enter the desired number of seats: ");
                    requiredSeats = Integer.parseInt(seatsInput);
                }

                String arrivalDate = null;
                if (arrivalDateCheckBox.isSelected()) {
                    arrivalDate = JOptionPane.showInputDialog("Enter the desired arrival time (HH:mm:ss): ");
                }

                String departureDate = null;
                if (departureDateCheckBox.isSelected()) {
                    departureDate = JOptionPane.showInputDialog("Enter the desired departure time (HH:mm:ss): ");
                }

                for (Flight flight : allFlights) {
                    boolean match = true;

                    if (arrivalDate != null && !flight.getArrivalTime().equals(arrivalDate)) {
                        match = false;
                    }

                    if (departureDate != null && !flight.getDepartureTime().equals(departureDate)) {
                        match = false;
                    }

                    if (requiredSeatsCheckBox.isSelected() && flight.getAvailableSeats() < requiredSeats) {
                        match = false;
                    }

                    if (match) {
                        filteredFlights.add(flight);
                    }
                }

                displayFlights();
            }
        });

        mainPanel.add(filtersPanel, BorderLayout.WEST);
        mainPanel.add(searchButton, BorderLayout.SOUTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        flightsTextArea = new JTextArea();
        flightsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(flightsTextArea);

        resultPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(resultPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);

        getAllFlights();
    }

    private void getAllFlights() {
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Flight")) {

            while (resultSet.next()) {
                int flightId = resultSet.getInt("FlightId");
                String flightDate = resultSet.getString("FlightDate");
                int availableSeats = resultSet.getInt("AvailableSeats");
                String arrivalTime = resultSet.getString("ArrivalTime");
                String departureTime = resultSet.getString("DepartureTime");
                String arrivalAirport = resultSet.getString("ArrivalAirport");
                String departureAirport = resultSet.getString("DepartureAirport");
                int airplaneId = resultSet.getInt("AirplaneId");
                int userId = resultSet.getInt("UserId");
                int airportId = resultSet.getInt("AirportId");

                Flight flight = new Flight(flightId, flightDate, availableSeats, arrivalTime, departureTime, arrivalAirport, departureAirport, airplaneId, userId, airportId);
                allFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filterByArrivalDate() {
        String arrivalDate = JOptionPane.showInputDialog("Enter the desired arrival time (HH:mm:ss): ");

        filteredFlights.removeIf(flight -> !flight.getArrivalTime().equals(arrivalDate));
    }

    private void filterByDepartureDate() {
        String departureDate = JOptionPane.showInputDialog("Enter the desired departure time (HH:mm:ss): ");
        filteredFlights.removeIf(flight -> !flight.getDepartureTime().equals(departureDate));
    }
    private void filterByRequiredSeats() {
        String seatsInput = JOptionPane.showInputDialog("Enter the desired number of seats: ");
        int requiredSeats = Integer.parseInt(seatsInput);
        filteredFlights.removeIf(flight -> flight.getAvailableSeats() < requiredSeats);
    }

    private void displayFlights() {
        StringBuilder flightsText = new StringBuilder("Filter Results\n");

        for (Flight flight : filteredFlights) {
            flightsText.append("Flight ID: ").append(flight.getFlightId())
                    .append(", Date: ").append(flight.getFlightDate())
                    .append(", No.Seats: ").append(flight.getAvailableSeats())
                    .append(", Airplane ID: ").append(flight.getAirplaneId())
                    .append(", User ID: ").append(flight.getUserId())
                    .append(", Arrival Time: ").append(flight.getArrivalTime())
                    .append(", Departure Time: ").append(flight.getDepartureTime())
                    .append("\n");
        }

        flightsTextArea.setText(flightsText.toString());
    }
}
