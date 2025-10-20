Overview

This project solves Assignment 1 of the Level 2 Coding Challenge using Java Streams.
It reads customer shopping data from a CSV file and performs grouping and aggregation.

Implemented Tasks

Read data from CSV into a collection;
Count population grouped by gender (transactions + unique customers);
Find total sales grouped by gender;
Find most used payment method;
Find day with the most sales.

How to Run

From project root (ASSIGNMENTSOLUTION_1):
javac -encoding UTF-8 -d bin src/\*.java
java -cp bin App
or specify path explicitly:
java -cp bin App src/customer_shopping_data.csv

Example Output

Loaded rows = 99457
Transactions: {Female=59482, Male=39975}
Total sales: {Female=$150,207,100.00, Male=$101,298,700.00}
Most used payment method: Cash
Top sales day: 2021-10-28 ($534,906.86)

Notes

Uses Java Streams (groupingBy, counting, reducing);
Amounts use BigDecimal for precision;
Supports multiple date formats: M/d/yyyy, d/M/yyyy, etc.;
Output formatted with commas for readability;
