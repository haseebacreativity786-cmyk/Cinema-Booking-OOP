# Cinema Booking System

A console-based Java application developed using Object-Oriented Programming concepts.
The system allows customers to view movies, select seats, book tickets, print receipts, and cancel bookings through a simple menu-driven interface.

---

## 👤 Student Information

| Name    | CMS/ID      | Section |
| ------- | ----------- | ------- |
| Haseeba | 023-25-0044 | D       |

---

## Project Purpose

The main purpose of this project is to solve the problem of manual cinema ticket booking by providing a simple and efficient console-based system. The application manages seat booking, receipt generation, cancellation, and booking records using Java.

---

## OOP Concepts Used

| OOP Concept        | Implementation                           |
| ------------------ | ---------------------------------------- |
| Abstraction        | `User` abstract class                    |
| Inheritance        | `Customer extends User`                  |
| Encapsulation      | Private variables with getters           |
| Polymorphism       | `printReceipt()` method                  |
| Interface          | `Printable` interface                    |
| Collections        | `ArrayList` used for data storage        |
| Exception Handling | Handles invalid input and booking errors |
| File Handling      | Bookings saved in `bookings.txt`         |

---

## Main Classes

* `User` → Abstract base class
* `Customer` → Stores customer details
* `Movie` → Stores movie information
* `Seat` → Manages seat booking status
* `Booking` → Handles booking and receipt printing
* `CinemaService` → Main booking management system
* `InputHelper` → Handles safe user input
* `Project` → Main driver class

---

## Features

* View available movies
* Display seat layout
* Book cinema tickets
* Automatic receipt generation
* 15% discount for booking 3 or more seats
* Cancel booking feature
* Prevent duplicate seat booking
* Save booking records in file
* Input validation using exception handling

---

## How to Run

### Requirements

* Java JDK 8 or above
* IntelliJ IDEA or any Java IDE

---

### Compile the Program

```bash
javac Project.java
```

### Run the Program

```bash
java Project
```

---

## Project Files

```text
Project.java
README.md
bookings.txt
```

---

##  Demo Video

YouTube video link will be added here.

---

##  GitHub Repository

GitHub repository link will be added here.

---

## Conclusion

This project demonstrates the practical implementation of Object-Oriented Programming concepts in Java. It provides a simple and efficient cinema booking system with features like booking management, cancellation, receipt generation, and file handling.
