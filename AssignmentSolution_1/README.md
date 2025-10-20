# Assignment 1 — Aggregation & Grouping with Java Streams

## Overview  
This project implements **Assignment 1** of the Level 2 Coding Challenge using **Java Streams**.  
It reads customer shopping data from a CSV file and performs grouping and aggregation operations.

---

## Implemented Tasks  
1. Read data from a CSV file into a collection  
2. Count population grouped by gender (transactions + unique customers)  
3. Find total sales grouped by gender  
4. Find the most used payment method  
5. Find the day with the highest total sales  

---

## How to Run  

From project root (`ASSIGNMENTSOLUTION_1`):  

**Compile**  
javac -encoding UTF-8 -d bin src/*.java  

**Run analysis**  
java -cp bin App  

**Or specify CSV path explicitly**  
java -cp bin App src/customer_shopping_data.csv  

---

## Example Output  

Loaded rows = 99457  
Transactions: {Female = 59482, Male = 39975}  

Total sales: {Female = $150,207,100.00, Male = $101,298,700.00}  

Most used payment method: Cash  

Top sales day: 2021-10-28 ($534,906.86)  

✅ All computations completed successfully — Aggregation and grouping verified.  

---

## Notes  
- Uses `Java Streams` (`groupingBy`, `counting`, `reducing`)  
- Uses `BigDecimal` for precision  
- Supports multiple date formats (`M/d/yyyy`, `d/M/yyyy`, etc.)  
- Output formatted with commas for readability  
