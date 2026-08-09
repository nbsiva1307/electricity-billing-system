# Electricity Billing System (AWT)

A Java AWT-based desktop application for generating electricity bills using customer details and meter readings. The system calculates chargeable units, applies slab-based rates, predicts next-month usage and bill amount, and provides a printable bill.

## Project Overview

The **Electricity Billing System** is a Java-based desktop application developed using **Java AWT**. It provides a graphical user interface for entering customer information and electricity meter readings.

The application calculates the electricity bill based on the difference between meter readings and applies predefined slab rates. It also provides an estimated next-month consumption and bill amount.

## Problem Statement

Manual electricity bill calculation can require repeated calculations and may lead to errors. This project provides a simple computerized solution to automate the billing process and generate a clear electricity bill through a graphical interface.

## Objective

- To develop an electricity billing application using Java AWT.
- To accept customer and meter-reading details.
- To calculate electricity consumption.
- To calculate the bill using slab-based rates.
- To predict next-month electricity usage.
- To estimate the next-month bill amount.
- To provide a bill-printing option.

## Key Features

- Customer ID input
- Customer name and address input
- Previous meter reading input
- Current meter reading input
- Automatic chargeable-unit calculation
- Free-unit deduction
- Slab-based billing
- Total bill calculation
- Next-month consumption prediction
- Next-month bill prediction
- Date and time generation
- Bill display using TextArea
- Printable electricity bill
- Simple Java AWT graphical interface

## Technologies Used

| Technology | Purpose |
|---|---|
| **Java** | Core programming language |
| **Java AWT** | Graphical user interface |
| **Frame** | Main application window |
| **TextField** | Customer and meter-reading input |
| **Button** | Generate Bill and Print Bill actions |
| **TextArea** | Display generated bill |
| **ActionListener** | Handles button events |
| **Printable** | Bill printing |
| **PrinterJob** | Printing support |
| **DecimalFormat** | Currency/amount formatting |
| **LocalDateTime** | Current date and time |

## System Workflow

```text
Customer Details
       ↓
Previous Reading + Current Reading
       ↓
Java AWT GUI
       ↓
Calculate Chargeable Units
       ↓
Apply Slab Rates
       ↓
Calculate Total Bill
       ↓
Predict Next Month Usage
       ↓
Calculate Predicted Bill
       ↓
Display Bill
       ↓
Print Bill
