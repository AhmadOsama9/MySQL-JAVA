package Classes;

import ConnectToDatabase.ConnectToDatabase;

import java.sql.*;

public class Ticket {
    private static int nextId = 1;
    private int ticketNumber;
    private String issueDate;
    private String ticketType;
    private String price;
    private int age;
    private int seatNum;
    private int bookingId;

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    public Ticket() {

    }

    public Ticket(String issueDate, String ticketType, String price, int age, int seatNum, int bookingId) {
        if (nextId == 1) {
            int maxId = retrieveMaxTicketId();
            nextId = maxId;
        }

        ticketNumber = ++nextId;

        this.bookingId = bookingId;
        this.issueDate = issueDate;
        this.ticketType = ticketType;
        this.price = price;
        this.age = age;
        this.seatNum = seatNum;
        this.bookingId = bookingId;
    }

    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getTicketType() {
        return ticketType;
    }
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSeatNum() {
        return seatNum;
    }
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int booking) {
        this.bookingId = booking;
    }

    public int retrieveMaxTicketId() {
        int maxId = 1;
        try (
                Connection conn = ConnectToDatabase.openConnection();
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("SELECT MAX(TicketNumber) FROM ticket")) {

            if (res.next()) {
                maxId = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
