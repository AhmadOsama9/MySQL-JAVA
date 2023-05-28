package Classes;


import ConnectToDatabase.ConnectToDatabase;

import java.sql.*;

public class Booking {
    private static int nextId = 1;
    private int bookingId;

    private int flightId;
    private String ticketId;
    private int numberOfSeats;
    Connection conn;


    public Booking() {
        conn = ConnectToDatabase.openConnection();
    }
    public Booking(int flightId, String ticketId, int numberOfSeats) {
        conn = ConnectToDatabase.openConnection();
        if (nextId == 1) {
            int maxId = retrieveMaxBookingId();
            nextId = maxId;
        }

        this.bookingId = ++nextId;
        this.flightId = flightId;
        this.ticketId = ticketId;
        this.numberOfSeats = numberOfSeats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return bookingId;
    }
    public void setId(int id) {
        this.bookingId = id;
    }
    public int getFlight() {
        return flightId;
    }
    public void setFlight(int flightId) {
        this.flightId = flightId;
    }
    public String getTicket() {
        return ticketId;
    }
    public void setTicket(String ticketId) {
        this.ticketId = ticketId;
    }
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        String query = "SELECT * FROM booking WHERE BookingId = ?";
        try (
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("BookingId");
                int numberOfSeats = resultSet.getInt("NumberOfSeats");
                int flightId = resultSet.getInt("flightId");
                String ticketId = resultSet.getString("ticketId");

                booking = new Booking(flightId, ticketId, numberOfSeats);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    public Ticket getTicketByBookingId(int BookId) {
        Ticket ticket = null;
        System.out.println("Getting the ticket with BookId " + BookId);
        String query = "SELECT * FROM ticket WHERE BookId = ?";
        try (
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, BookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String issueDate = resultSet.getString("issueDate");
                String ticketType = resultSet.getString("ticketType");
                String price = resultSet.getString("Price");
                int age = resultSet.getInt("age");
                int seatNum = resultSet.getInt("seatNumber");

                ticket = new Ticket(issueDate, ticketType, price, age, seatNum, BookId);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("It finished the query");
        return ticket;
    }

    public int retrieveMaxBookingId() {
        int maxId = 1;
        try (
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("SELECT MAX(BookingId) FROM booking")) {

            if (res.next()) {
                maxId = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public void updateBooking() {
        String query = "UPDATE booking SET NumberOfSeats = ? WHERE BookingId = ?";

        try (
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.getNumberOfSeats());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking updated successfully.");
            } else {
                System.out.println("No rows were affected. Booking not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating flight in the database: " + e.getMessage());
        }
    }
    public String changeTicketType(String classType, int bookingId) {
        String query = "UPDATE ticket SET TicketType = ? WHERE BookId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, classType);
            stmt.setInt(2, bookingId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                String result = "Ticket updated successfully.";
                System.out.println(result);
                return result;
            } else {
                String result = "No rows were affected. Ticket not found.";
                System.out.println(result);
                return result;
            }
        } catch (SQLException e) {
            String result = "Error updating ticket in the database: " + e.getMessage();
            System.out.println(result);
            return result;
        }
    }

    public void addBooking() {
        String query = "INSERT INTO booking (BookingId, NumberOfSeats, flightId, ticketId) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.getId());
            stmt.setInt(2, this.getNumberOfSeats());
            stmt.setInt(3, this.getFlightId());
            stmt.setString(4, this.getTicketId());
            stmt.executeUpdate();
            System.out.println("Booking added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding booking to the database: " + e.getMessage());
        }
    }

    public void addTicket(Ticket ticket) {
        String query = "INSERT INTO ticket (issueDate, ticketType, Price, age, seatNumber, BookId, TicketNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            Booking booking = getBookingById(ticket.getBookingId());
            if (booking == null) {
                System.out.println("Error adding ticket. Booking does not exist.");
                return;
            }

            stmt.setString(1, ticket.getIssueDate());
            stmt.setString(2, ticket.getTicketType());
            stmt.setString(3, ticket.getPrice());
            stmt.setInt(4, ticket.getAge());
            stmt.setInt(5, ticket.getSeatNum());
            stmt.setInt(6, ticket.getBookingId());
            stmt.setInt(7, ticket.getTicketNumber());
            stmt.executeUpdate();
            System.out.println("Ticket added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding ticket to the database: " + e.getMessage());
        }
    }
    public String removeBooking(int bookingId) {
        System.out.println("Removing Booking with Id " + bookingId);
        String bookingQuery = "DELETE FROM booking WHERE BookingId = ?";
        String ticketQuery = "DELETE FROM ticket WHERE BookId = ?";

        try (
                PreparedStatement bookingStmt = conn.prepareStatement(bookingQuery);
                PreparedStatement ticketStmt = conn.prepareStatement(ticketQuery)
        ) {

            ticketStmt.setInt(1, bookingId);
            int ticketsDeleted = ticketStmt.executeUpdate();

            bookingStmt.setInt(1, bookingId);
            int bookingDeleted = bookingStmt.executeUpdate();

            if (bookingDeleted > 0) {
                String result = "Booking removed successfully.\n";
                result += "Tickets deleted: " + ticketsDeleted;
                System.out.println(result);
                return result;
            } else {
                String result = "No booking found with the provided ID.";
                System.out.println(result);
                return result;
            }
        } catch (SQLException e) {
            String result = "Error removing booking from the database: " + e.getMessage();
            System.out.println(result);
            return result;
        }
    }

}

