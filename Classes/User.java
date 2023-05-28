package Classes;

import ConnectToDatabase.ConnectToDatabase;

import java.sql.*;

public class User {
    private static int nextId = 1;
    private int id;
    private String name;

    private String phone;
    private String email;
    private String password;
    private String userRole;
    private String payment;
    private int bookingId;

    private Connection conn;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    private int age;
    private Address address;


    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public User(String name, String phone, String email, String password, String userRole, int age, String payment, Address address) {
        if(nextId == 1) {
            int maxId = retrieveMaxUserIdFromDatabase();
            nextId = maxId;
        }
        this.id = ++nextId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.age = age;
        this.payment = payment;
        this.address = address;

        conn = ConnectToDatabase.openConnection();
    }

    public User() {
        conn = ConnectToDatabase.openConnection();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int retrieveMaxUserIdFromDatabase() {
        int maxId = 0;
        try (Connection conn = ConnectToDatabase.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT MAX(userId) FROM Users")) {

            if (res.next()) {
                maxId = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }

    public void addUser() {
        String query = "INSERT INTO Users (userId, name, phone, email, pass, userRole, age, payment, Country, City, Street) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.getId());
            stmt.setString(2, this.getName());
            stmt.setString(3, this.getPhone());
            stmt.setString(4, this.getEmail());
            stmt.setString(5, this.getPassword());
            stmt.setString(6, this.getUserRole());
            stmt.setInt(7, this.getAge());
            stmt.setString(8, this.getPayment());
            stmt.setString(9, this.getAddress().getCountry());
            stmt.setString(10, this.getAddress().getCity());
            stmt.setString(11, this.getAddress().getStreet());

            stmt.executeUpdate();

            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding user to the database: " + e.getMessage());
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        Address address = null;

        try {
            String sql = "SELECT * FROM Users WHERE Email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                int id = res.getInt("userId");
                String name = res.getString("name");
                String phone = res.getString("phone");
                String password = res.getString("Pass");
                String userRole = res.getString("userRole");
                int age = res.getInt("Age");
                String payment = res.getString("payment");
                int bookingId = res.getInt("BookingId");

                // Retrieve address information from the database
                String street = res.getString("Street");
                String city = res.getString("City");
                String country = res.getString("Country");
                address = new Address(country, city, street);

                user = new User(name, phone, email, password, userRole, age, payment, address);
                user.setId(id);
            }

            res.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
