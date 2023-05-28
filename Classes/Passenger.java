package Classes;
import java.util.Scanner;

public class Passenger extends User {

    public Passenger(String name, String phone, String email, String password, String role, int age, String payment, Address address) {
        super(name, phone, email, password, role, age, payment, address);
    }
    public void bookFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter flight ID: ");
        int flightId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter ticket ID: ");
        String ticketId = scanner.nextLine();
        System.out.print("Enter number of seats: ");
        int numberOfSeats = scanner.nextInt();

        Booking booking = new Booking(flightId, ticketId, numberOfSeats);
        booking.addBooking();
        Ticket ticket = new Ticket("2023-01-1", "business", "2000EGP", 21, 2, booking.getBookingId());
        booking.addTicket(ticket);

    }

    public void cancelBooking() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking ID to cancel: ");
        int bookingId = scanner.nextInt();

        Booking booking = new Booking();
        booking.removeBooking(bookingId);

    }

    public void viewBookings() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking ID to view details: ");
        int bookingId = scanner.nextInt();

        Booking booking = new Booking();
        booking = booking.getBookingById(bookingId);
        if (booking != null) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Booking Flight ID: " + booking.getFlightId());
        } else {
            System.out.println("No bookings found for the passenger.");
        }
    }
}
