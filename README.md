# Cash Register Security App

This app is designed to ensure cash register security and session management in a modern POS (Point of Sale) system. Its primary goal is to ensure that the previous day's register is properly closed before starting sales for the current day.

## Purpose
In retail shops, operators often forget to close the previous day's register before starting the next day's work, which can lead to accounting errors or security risks. This app:
*   Prevents session overlapping.
*   Ensures a session is valid only for a specific day.
*   Forces the operator to complete the previous day's tasks before starting a new one.

## How it works
The app operates through three main states:

1.  **CLOSED State:** Initially, the register is closed. The user must click the "Open Register" button to begin.
2.  **ACTIVE State:** Once opened, the session becomes active, and the POS Dashboard is unlocked. A "Simulate Next Day" button is provided to test what happens when the day changes.
3.  **STALE State:** If a session is left open and the user returns the next day, the app displays "ACCESS DENIED!" because the previous day's session is now "Stale." The user must close the yesterday's register to proceed.

## Tech Stack
*   **Kotlin:** For the core application logic.
*   **Jetpack Compose:** For building a modern and dynamic User Interface (UI).
*   **ViewModel:** For managing application state and data.
*   **Material 3:** Following Google's latest design guidelines.
*   **Java Time API (LocalDate):** For handling dates and time-based logic.

## Features
*   **Real-time State Management:** UI updates automatically based on the register's state.
*   **Session Security:** Prevents starting new work until the previous day's session is closed.
*   **Time Simulation:** Built-in tool for developers and testers to simulate the "Next Day" scenario.

---
This is a practice project built to explore and learn state management in Kotlin and Jetpack Compose.
