# Billing Calculation

## Overview

The Electricity Billing System calculates the electricity bill based on the meter reading and predefined slab rates. The program also provides a prediction of next-month electricity usage and the estimated bill amount.

## 1. Meter Reading

The system accepts:

- Previous Reading
- Current Reading

The readings are entered through the Java AWT graphical interface.

## 2. Free Units

The system provides **100 free units** before calculating the chargeable amount.

```text
Chargeable Units =
Current Reading - 100
