package Classes;

import ConnectToDatabase.ConnectToDatabase;

import java.sql.*;
import java.util.ArrayList;

public class Flight {
    private static int nextId = 1;
    private int thisId;
    private String thisDate;
    private int availableSeats;
    private String arrivalTime;
    private String departureTime;
    private String arrivalAirport;
    private String departureAirport;
    private int airplaneId;
    private int userId;
    private int airportId;


    private Connection conn;

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Flight.nextId = nextId;
    }

    public int getFlightId() {
        return thisId;
    }

    public void setFlightId(int thisId) {
        this.thisId = thisId;
    }

    public String getFlightDate() {
        return thisDate;
    }

    public void setFlightDate(String thisDate) {
        this.thisDate = thisDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(int airplaneId) {
        this.airplaneId = airplaneId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public Flight() {
        conn = ConnectToDatabase.openConnection();
    }
    public Flight(String thisDate, int availableSeats, String arrivalTime, String departureTime,
                  String arrivalAirport, String departureAirport, int airplaneId, int userId, int airportId) {
        if (nextId == 1) {
            int maxId = retrieveMaxUserIdFromDatabase();
            nextId = maxId;
        }
        this.thisId = ++nextId;
        this.thisDate = thisDate;
        this.availableSeats = availableSeats;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.airplaneId = airplaneId;
        this.userId = userId;
        this.airportId = airportId;

        conn = ConnectToDatabase.openConnection();

    }
    public Flight(int id, String thisDate, int availableSeats, String arrivalTime, String departureTime,
                  String arrivalAirport, String departureAirport, int airplaneId, int userId, int airportId) {
        this.thisId = id;
        this.thisDate = thisDate;
        this.availableSeats = availableSeats;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.airplaneId = airplaneId;
        this.userId = userId;
        this.airportId = airportId;
    }


    private int retrieveMaxUserIdFromDatabase() {
        int maxId = 1;
        try (Connection conn = ConnectToDatabase.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT COALESCE(MAX(FlightId), 1) FROM Flight")) {

            if (res.next()) {
                maxId = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }


    public void addFlight() {
        String query = "INSERT INTO Flight (FlightId, FlightDate, AvailableSeats, ArrivalTime, DepartureTime, ArrivalAirport, DepartureAirport, AirplaneId, UserId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.getFlightId());
            stmt.setString(2, this.getFlightDate());
            stmt.setInt(3, this.getAvailableSeats());
            stmt.setString(4, this.getArrivalTime());
            stmt.setString(5, this.getDepartureTime());
            stmt.setString(6, this.getArrivalAirport());
            stmt.setString(7, this.getDepartureAirport());
            stmt.setInt(8, this.getAirplaneId());
            stmt.setInt(9, this.getUserId());

            stmt.executeUpdate();
            System.out.println("Flight added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding flight to the database: " + e.getMessage());
        }
    }

    public Flight getFlightById(int id) {
        Flight flight = null;
        String query = "SELECT * FROM Flight WHERE FlightId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int flightId = res.getInt("FlightId");
                String flightDate = res.getString("FlightDate");
                int availableSeats = res.getInt("AvailableSeats");
                String arrivalTime = res.getString("ArrivalTime");
                String departureTime = res.getString("DepartureTime");
                String arrivalAirport = res.getString("ArrivalAirport");
                String departureAirport = res.getString("DepartureAirport");
                int airplaneId = res.getInt("AirplaneId");
                int userId = res.getInt("UserId");
                int airportId = res.getInt("AirportId");

                flight = new Flight(flightDate, availableSeats, arrivalTime, departureTime, arrivalAirport, departureAirport, airplaneId, userId, airportId);
                flight.setFlightId(flightId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public void updateFlight() {
        String query = "UPDATE Flight SET DepartureTime = ?, ArrivalTime = ? WHERE FlightId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.getDepartureTime());
            stmt.setString(2, this.getArrivalTime());
            stmt.setInt(3, this.getFlightId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Flight updated successfully.");
            } else {
                System.out.println("No rows were affected. Flight not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating flight in the database: " + e.getMessage());
        }
    }

}
