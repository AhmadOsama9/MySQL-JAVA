package Classes;

import ConnectToDatabase.ConnectToDatabase;

import java.sql.*;
import java.util.ArrayList;

public class Airport {
    private static int nextId = 1;
    private int airportId;
    private String airportName;
    private String city;
    private String country;

    private Connection conn;

    public Airport(String name, String city, String country) {
        if(nextId == 1) {
            int maxId = retrieveMaxUserIdFromDatabase();
            nextId = maxId;
        }

        this.airportId = ++nextId;
        this.airportName = name;
        this.city = city;
        this.country = country;

        conn = ConnectToDatabase.openConnection();

    }

    public int retrieveMaxUserIdFromDatabase() {
        int maxId = 1;
        try (Connection conn = ConnectToDatabase.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT MAX(AirportId) FROM Airport")) {

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
        Airport.nextId = nextId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


        public void addAirport() {
            String query = "INSERT INTO Airport (AirportId, AirportName, City, Country) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, this.getAirportId());
                stmt.setString(2, this.getAirportName());
                stmt.setString(3, this.getCity());
                stmt.setString(4, this.getCountry());

                stmt.executeUpdate();
                System.out.println("Airport added successfully.");
            } catch (SQLException e) {
                System.out.println("Error adding airport to the database: " + e.getMessage());
            }
        }

    public Airport getAirportById(int airportId) {
        Airport airport = null;
        String query = "SELECT * FROM Airport WHERE AirportId = ?";
        try (
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, airportId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String airportName = resultSet.getString("AirportName");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");


                airport = new Airport(airportName, city, country);
                airport.setAirportId(airportId);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return airport;
    }



}
