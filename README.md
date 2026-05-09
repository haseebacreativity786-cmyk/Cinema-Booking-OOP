# Cinema Booking System

A console-based Java application for booking cinema tickets.
Customers can view available movies, select seats, receive printed receipts, and cancel bookings.

---

## Group Members

| Name    | CMS/ID        | Section |
|---------|---------------|---------|
| Haseeba | 023-25-0044   | D       |

---

## Purpose

This system solves the problem of manual cinema seat booking by providing a simple, menu-driven console application. Users can browse movies, pick seats, get a receipt, and cancel if needed.

---

## OOP Features Used

| Concept | Where Used |
|---------|-----------|
| Abstract Class | `User` — base class with abstract `showDetails()` |
| Inheritance | `Customer extends User` |
| Interface | `Printable` — implemented by `Booking` |
| Encapsulation | Private fields with getters/setters in all classes |
| Polymorphism | `printReceipt()` called via `Printable` reference |
| Collections | `ArrayList` for movies, seats, and bookings |
| File I/O | Bookings saved to `bookings.txt` |
| Exception Handling | Invalid seats, duplicate bookings, cancellation errors |

---

## Core Modules

- **`User` / `Customer`** — abstract user model with customer details
- **`Movie` / `Seat`** — movie info and seat availability tracking
- **`Booking`** — handles booking logic, pricing, discount, and receipt printing
- **`CinemaService`** — main service layer managing movies, bookings, and file saving
- **`InputHelper`** — validates all user input (no crashes on bad input)

---

## How to Run

**Requirements:** JDK 8 or above

```bash
javac Project.java
java Project
```

No external libraries required. `bookings.txt` is auto-created on first booking.

---

## Demo Video

> YouTube link — coming soon

---

## GitHub Repository

https://github.com/haseebacreativity786-cmyk/Cinema-Booking-OOP
