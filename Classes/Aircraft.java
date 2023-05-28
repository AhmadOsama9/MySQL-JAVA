package Classes;

import ConnectToDatabase.ConnectToDatabase;

import java.sql.*;
import java.util.ArrayList;

public class Aircraft {
    private static int nextId = 1;
    private int airplaneId;
    private String model;
    private int numberOfSeats;
    private String airplaneStatus;
    private int userId;

    private Connection conn;

    public Aircraft() {
        conn = ConnectToDatabase.openConnection();
    }
    public Aircraft(String model, int numberOfSeats, String airplaneStatus, int userId) {
        if (nextId == 1) {
            int maxId = retrieveMaxAirplaneIdFromDatabase();
            nextId = maxId;
        }

        this.airplaneId = ++nextId;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.airplaneStatus = airplaneStatus;
        this.userId = userId;

        conn = ConnectToDatabase.openConnection();
    }

    public int retrieveMaxAirplaneIdFromDatabase() {
        int maxId = 1;
        try (Connection conn = ConnectToDatabase.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT MAX(AirplaneId) FROM Airplane")) {

            if (res.next()) {
                maxId = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Aircraft.nextId = nextId;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(int airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getAirplaneStatus() {
        return airplaneStatus;
    }

    public void setAirplaneStatus(String airplaneStatus) {
        this.airplaneStatus = airplaneStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void addAircraft() {
        String query = "INSERT INTO Airplane (AirplaneId, Model, NumberOfSeats, AirplaneStatus, UserId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.getAirplaneId());
            stmt.setString(2, this.getModel());
            stmt.setInt(3, this.getNumberOfSeats());
            stmt.setString(4, this.getAirplaneStatus());
            stmt.setInt(5, this.getUserId());

            stmt.executeUpdate();
            System.out.println("Aircraft added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding aircraft to the database: " + e.getMessage());
        }
    }

    public Aircraft getAircraftById(int airplaneId) {
        Aircraft aircraft = null;
        String query = "SELECT * FROM Airplane WHERE AirplaneId = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, airplaneId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String model = resultSet.getString("Model");
                int numberOfSeats = resultSet.getInt("NumberOfSeats");
                String airplaneStatus = resultSet.getString("AirplaneStatus");
                int userId = resultSet.getInt("UserId");

                aircraft = new Aircraft(model, numberOfSeats, airplaneStatus, userId);
                aircraft.setAirplaneId(airplaneId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aircraft;
    }

    public ArrayList<Aircraft> getAllAircraftArray() {
        ArrayList<Aircraft> allAircraft = new ArrayList<>();
        String query = "SELECT * FROM Airplane";
        try (Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(query)) {

            while (res.next()) {
                int airplaneId = res.getInt("AirplaneId");
                String model = res.getString("Model");
                int numberOfSeats = res.getInt("NumberOfSeats");
                String airplaneStatus = res.getString("AirplaneStatus");
                int userId = res.getInt("UserId");

                Aircraft aircraft = new Aircraft(model, numberOfSeats, airplaneStatus, userId);
                aircraft.setAirplaneId(airplaneId);
                allAircraft.add(aircraft);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAircraft;
    }

    public void updateAircraft() {
        String query = "UPDATE Airplane SET Model = ?, NumberOfSeats = ?, AirplaneStatus = ? WHERE AirplaneId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.getModel());
            stmt.setInt(2, this.getNumberOfSeats());
            stmt.setString(3, this.getAirplaneStatus());
            stmt.setInt(4, this.getAirplaneId());

            stmt.executeUpdate();
            System.out.println("Aircraft updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating aircraft in the database: " + e.getMessage());
        }
    }
}
