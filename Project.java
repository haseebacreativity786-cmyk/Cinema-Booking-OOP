import java.util.*;
import java.io.*;

interface Printable {
    void printReceipt();
}
abstract class User {
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }

    public abstract void showDetails();
}
class Customer extends User {
    private String email;
    public Customer(String name, String phoneNumber, String email) {
        super(name, phoneNumber);
        this.email = email;
    }
    public void showDetails() {
        System.out.println("Customer Name: " + getName());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Email: " + email);
    }
}
class Seat {
    private int seatNumber;
    private boolean isBooked;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }
    public int getSeatNumber() { return seatNumber; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
}
class Movie {
    private String title;
    private String showTime;
    private ArrayList<Seat> seats;

    public Movie(String title, String showTime, int totalSeats) {
        this.title = title;
        this.showTime = showTime;
        seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }
    public String getTitle() { return title; }
    public String getShowTime() { return showTime; }
    public ArrayList<Seat> getSeats() { return seats; }
    public void displaySeats() {
        System.out.println("\n--- Seat Layout for " + title + " ---");
        for (Seat s : seats) {
            String status = s.isBooked() ? "[X]" : "[" + s.getSeatNumber() + "]";
            System.out.print(status + " ");
            if (s.getSeatNumber() % 5 == 0) System.out.println();
        }
        System.out.println();
    }
}
class Booking implements Printable {
    private User customer;
    private Movie movie;
    private ArrayList<Integer> seats;
    private double originalPrice;
    private double finalPrice;
    private double discount;
    private boolean cancelled;
    public Booking(User customer, Movie movie, ArrayList<Integer> seats) {
        this.customer = customer;
        this.movie = movie;
        this.seats = seats;
        this.cancelled = false;
        calculatePrice();
    }
    private void calculatePrice() {
        double pricePerSeat = 500;
        originalPrice = seats.size() * pricePerSeat;
        discount = (seats.size() >= 3) ? originalPrice * 0.15 : 0;
        finalPrice = originalPrice - discount;
    }
    public User getCustomer() { return customer; }
    public Movie getMovie() { return movie; }
    public ArrayList<Integer> getSeats() { return seats; }
    public double getOriginalPrice() { return originalPrice; }
    public double getFinalPrice() { return finalPrice; }
    public double getDiscount() { return discount; }
    public boolean isCancelled() { return cancelled; }
    public void cancel() {
        this.cancelled = true;
        for (int seatNum : seats) {
            movie.getSeats().get(seatNum - 1).setBooked(false);
        }
        System.out.println("Booking cancelled for " + customer.getName() + " | Movie: " + movie.getTitle());
    }
    @Override
    public void printReceipt() {
        System.out.println("\n=================================");
        System.out.println("        CINEMA TICKET");
        System.out.println("=================================");
        System.out.println("Customer : " + customer.getName());
        System.out.println("Phone    : " + customer.getPhoneNumber());
        System.out.println("Movie    : " + movie.getTitle());
        System.out.println("Time     : " + movie.getShowTime());
        System.out.println("Seats    : " + seats);
        System.out.println("---------------------------------");
        System.out.printf("Original Price : Rs. %.2f%n", originalPrice);
        if (discount > 0) {
            System.out.printf("Discount (15%%) : -Rs. %.2f%n", discount);
        } else {
            System.out.println("Discount       : Rs. 0.00");
        }
        System.out.printf("Final Price    : Rs. %.2f%n", finalPrice);
        System.out.println("==================================");
        System.out.println("Thank you for choosing our cinema!");
        System.out.println("==================================");
    }
    public String toString() {
        String status = cancelled ? "[CANCELLED]" : "[ACTIVE]";
        return status + " " + customer.getName() + " | " + movie.getTitle() + " | Seats: " + seats;
    }
}
class CinemaService {
    private ArrayList<Movie> movies;
    private ArrayList<Booking> allBookings;
    public CinemaService() {
        movies = new ArrayList<>();
        allBookings = new ArrayList<>();
        setupMovies();
    }
    private void setupMovies() {
        movies.add(new Movie("Batman",         "10:00 AM", 20));
        movies.add(new Movie("Inception",      "12:00 PM", 20));
        movies.add(new Movie("Avatar 2",       "02:00 PM", 20));
        movies.add(new Movie("Interstellar",   "04:00 PM", 20));
        movies.add(new Movie("Joker",          "06:00 PM", 20));
        movies.add(new Movie("Spiderman",      "08:00 PM", 20));
        movies.add(new Movie("Fast & Furious", "10:00 PM", 20));
        movies.add(new Movie("Titanic",        "11:30 PM", 20));
    }
    public void showMovies() {
        System.out.println("\n--- Available Movies ---");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle() +
                    " (" + movies.get(i).getShowTime() + ")");
        }
    }
    public Movie getMovie(int index) {
        if (index >= 0 && index < movies.size()) return movies.get(index);
        return null;
    }
    public void processBooking(User user, Movie movie, ArrayList<Integer> seatNumbers) throws Exception {
        ArrayList<Seat> movieSeats = movie.getSeats();
        ArrayList<Integer> invalidSeats = new ArrayList<>();
        for (int num : seatNumbers) {
            if (num < 1 || num > movieSeats.size()) {
                invalidSeats.add(num);
            }
        }
        if (!invalidSeats.isEmpty()) {
            throw new Exception("Invalid seat numbers: " + invalidSeats);
        }
        ArrayList<Integer> alreadyBooked = new ArrayList<>();
        for (int num : seatNumbers) {
            if (movieSeats.get(num - 1).isBooked()) {
                alreadyBooked.add(num);
            }
        }
        if (!alreadyBooked.isEmpty()) {
            throw new Exception("These seats are already booked: " + alreadyBooked);
        }
        for (int num : seatNumbers) {
            movieSeats.get(num - 1).setBooked(true);
        }
        Booking b = new Booking(user, movie, seatNumbers);
        allBookings.add(b);
        saveBookingToFile(b);

        System.out.println("\nBooking successful!");
    }
    public void showAllBookings() {
        if (allBookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (int i = 0; i < allBookings.size(); i++) {
            System.out.println((i + 1) + ". " + allBookings.get(i));
        }
    }
    public void cancelBooking(int index) throws Exception {
        if (index < 0 || index >= allBookings.size()) {
            throw new Exception("Invalid booking number!");
        }
        Booking b = allBookings.get(index);
        if (b.isCancelled()) {
            throw new Exception("Booking is already cancelled!");
        }
        b.cancel();
        saveBookingToFile(b);
    }public ArrayList<Booking> getBookings() { return allBookings; }
 private void saveBookingToFile(Booking b) {
        try (PrintWriter out = new PrintWriter(new FileWriter("bookings.txt", true))) {
            out.println(b.toString());
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }
}
class InputHelper {
    private Scanner sc;

    public InputHelper(Scanner sc) {
        this.sc = sc;
    }
    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }}
    public String getString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
    public String getEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = sc.nextLine().trim();
            if (email.contains("@") && email.contains(".")) {
                return email;
            }
            System.out.println("Invalid email! Please enter a valid email (e.g. abc@gmail.com).");
        }
    }
}
public class Project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputHelper input = new InputHelper(sc); 
        CinemaService cinema = new CinemaService();
        System.out.println("=============================");
        System.out.println("   Welcome to Cinema System  ");
        System.out.println("=============================");
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. View Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. View All Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            int choice = input.getInt("Choose: ");
            switch (choice) {
                case 1:
                    cinema.showMovies();
                    break;
                case 2:
                    String name  = input.getString("Enter your name: ");
                    String phone = input.getString("Enter your phone: ");
                    String email = input.getEmail("Enter your email: ");
                    User user = new Customer(name, phone, email);
                    cinema.showMovies();
                    int m = input.getInt("Select movie (number): ") - 1;
                    Movie movie = cinema.getMovie(m);
                    if (movie == null) {
                        System.out.println("Invalid movie selection!");
                        break;
                    }
                    movie.displaySeats();
                    int count = input.getInt("How many seats to book: ");
                    if (count <= 0) {
                        System.out.println("Seat count must be at least 1.");
                        break;
                    }
                    ArrayList<Integer> seats = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        int seatNum = input.getInt("Enter seat number " + (i + 1) + ": ");
                        seats.add(seatNum);
                    }
                    try {
                        cinema.processBooking(user, movie, seats);
                        // Auto print receipt using Printable interface
                        Booking lastBooking = cinema.getBookings()
                                .get(cinema.getBookings().size() - 1);
                        lastBooking.printReceipt();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    cinema.showAllBookings();
                    break;
                case 4:
                    cinema.showAllBookings();
                    if (cinema.getBookings().isEmpty()) break;

                    int bookingNum = input.getInt("Enter booking number to cancel: ") - 1;
                    try {
                        cinema.cancelBooking(bookingNum);
                        System.out.println("Booking cancelled successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thankyou For choosing us!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }
}
