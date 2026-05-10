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

**Solution:** This system automates the cinema booking process through a console menu. It tracks seat availability in real time, applies discounts automatically, prints receipts, handles cancellations with refund calculation, and saves all bookings to a file.

**Users:**
- **Customers**  people who want to book, view, or cancel cinema tickets
- **Cinema Staff**  can view all bookings from the saved file (`bookings.txt`)

---

## Core Modules: Main Classes & Their Roles

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

## What This System Does Full Feature List

### 1. View Movies
- Displays a numbered list of 8 available movies with their showtimes
- Movies: Batman, Inception, Avatar 2, Interstellar, Joker, Spiderman, Fast & Furious, Titanic

### 2. Book a Ticket
- Customer enters their **name, phone number, and email**
- Email is validated must contain `@` and `.`
- Customer selects a movie and views the **live seat layout**
  - Available seats shown as `[1]`, booked seats shown as `[X]`
- Customer enters seat numbers one by one
- System checks for invalid or already-booked seats and shows clear error messages

> **Discount Feature:** If a customer books **3 or more seats**, a **15% discount** is automatically applied on the total price.
>
> Example: 3 seats × Rs. 500 = Rs. 1500 → Discount Rs. 225 → **Final Price Rs. 1275**

- Receipt is printed automatically showing original price, discount, and final price

### 3. View All Bookings
- Displays all bookings with status: `[ACTIVE]` or `[CANCELLED]`
- Shows customer name, movie, and seat numbers

### 4. Cancel a Booking

>  **Cancellation Fee Feature:** When a booking is cancelled, a **10% cancellation fee** is deducted from the amount paid. The remaining amount is returned as refund.
>
> Example: Paid Rs. 1000 → Cancellation Fee Rs. 100 (10%) → **Refund Rs. 900**

- Cancelled seats are freed up automatically for other customers to book
- A cancellation summary is printed showing fee deducted and refund amount

### 5. Auto Save to File
- Every booking and cancellation is automatically saved to `bookings.txt`
- No setup needed file is created automatically

### 6. Input Validation
- Wrong input (letters instead of numbers) never crashes the program
- Email format is validated before accepting

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

```bash
# Step 1 — Compile
javac Project.java

# Step 2 — Run
java Project
```

**Menu:**
```
1. View Movies
2. Book Ticket
3. View All Bookings
4. Cancel Booking
5. Exit
```

> No database or config file needed. `bookings.txt` is auto created on first booking.

---

## Demo Video

https://drive.google.com/file/d/1nkzI3Lhmkj6BklvBmoAMUd8Z1geRKU5A/view?usp=sharing

---

## 🔗 GitHub Repository

https://github.com/haseebacreativity786-cmyk/Cinema-Booking-OOP
