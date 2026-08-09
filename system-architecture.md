# System Architecture
![Uploading image.png…]()

## Architecture Diagram

```text
+----------------------+
|   Customer Details   |
|----------------------|
| Customer ID          |
| Name                 |
| Address              |
| Previous Reading     |
| Current Reading      |
+----------+-----------+
           |
           v
+----------------------+
|     Java AWT GUI     |
|      Eseawt.java     |
+----------+-----------+
           |
           v
+----------------------+
| Billing Calculation  |
|----------------------|
| Unit Calculation     |
| Free Unit Deduction  |
| Slab-Based Charges   |
+----------+-----------+
           |
           +----------------------+
           |                      |
           v                      v
+-------------------+    +----------------------+
| Current Bill      |    | Next Month Prediction|
| Total Amount      |    | Units & Amount       |
+---------+---------+    +----------+-----------+
          |                         |
          +------------+------------+
                       |
                       v
              +----------------+
              |  Bill Display  |
              |   TextArea     |
              +-------+--------+
                      |
                      v
              +----------------+
              |   Print Bill   |
              | PrinterJob API |
              +----------------+
