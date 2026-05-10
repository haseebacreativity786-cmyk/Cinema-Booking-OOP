# Cinema Booking System

A console based Java application that allows customers to view movies, book seats, receive printed receipts, and cancel bookings all through a simple text menu.

---

## Member Detail

| Name    | CMS/ID      | Section |
|---------|-------------|---------|
| Haseeba | 023-25-0044 | D       |

---

## Purpose: What problem does this solve? Who are the users?

**Problem:** Manual cinema seat booking is slow and error prone. There is no easy way to check seat availability, apply discounts, or keep a record of bookings.

**Solution:** This system automates the cinema booking process through a console menu. It tracks seat availability in real time, applies discounts automatically, prints receipts, and saves all bookings to a file.

**Users:**
- **Customers** people who want to book, view, or cancel cinema tickets
- **Cinema Staff** can view all bookings from the saved file (`bookings.txt`)

---

## Core Modules Main Classes & Their Roles

| Class | Role |
|-------|------|
| `User` | Abstract base class stores name and phone number |
| `Customer` | Extends `User` adds email, represents a real customer |
| `Seat` | Represents one seat tracks seat number and booked status |
| `Movie` | Holds movie title, showtime, and list of seats |
| `Booking` | Handles booking logic pricing, discount, receipt printing, cancellation |
| `CinemaService` | Main service layer manages all movies and bookings, saves to file |
| `InputHelper` | Handles and validates all user input from console |

**Flow:**
```
main() → CinemaService → Movie → Seat
                      → Booking (implements Printable)
                      → Customer (extends User)
                      → InputHelper (validates input)
```

---

## Key OOP Features

### 1. Abstract Class
`User` is abstract defines common fields (name, phone) and forces subclasses to implement `showDetails()`.
```java
abstract class User {
    private String name;
    private String phoneNumber;
    public abstract void showDetails();
}
```

### 2. Inheritance
`Customer` extends `User` and adds email field with its own `showDetails()` implementation.
```java
class Customer extends User { ... }
```

### 3. Interface
`Printable` interface is implemented by `Booking` to print formatted receipts.
```java
interface Printable {
    void printReceipt();
}
class Booking implements Printable { ... }
```

### 4. Encapsulation
All fields in every class are `private` with public getters/setters no direct access from outside.
```java
private int seatNumber;
private boolean isBooked;
public int getSeatNumber() { return seatNumber; }
public void setBooked(boolean booked) { isBooked = booked; }
```

### 5. Polymorphism
`printReceipt()` is called through the `Printable` interface reference on a `Booking` object.

### 6. Collections
`ArrayList` used for storing movies, seats per movie, and all bookings.

### 7. Exception Handling
Custom exceptions thrown for:
- Invalid seat numbers
- Already booked seats
- Cancelling an already cancelled booking
```java
throw new Exception("These seats are already booked: " + alreadyBooked);
```

### 8. File I/O
Every booking (and cancellation) is automatically saved to `bookings.txt` using `FileWriter` and `PrintWriter`.

---

## How to Run

**Requirements:** JDK 8 or above (no external libraries needed)

### Step 1: Compile
```bash
javac Project.java
```

### Step 2: Run
```bash
java Project
```

### Step 3: Use the Menu
```
1. View Movies
2. Book Ticket
3. View All Bookings
4. Cancel Booking
5. Exit
```

> No database or config file needed. `bookings.txt` is auto-created on first booking.

---

## Demo Video

> YouTube link — coming soon

---

## GitHub Repository

https://github.com/haseebacreativity786-cmyk/Cinema-Booking-OOP
