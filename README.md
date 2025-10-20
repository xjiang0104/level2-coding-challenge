Overview

This repository contains my Level 2 Coding Challenge completed in Java.
It includes two assignments that demonstrate data processing and concurrency using core Java features only.

Assignments
1. Aggregation & Grouping with Java Streams

Reads a CSV dataset containing customer shopping data
Performs aggregation and grouping using Java Streams
Tasks implemented:
Read data from CSV
Count population by gender (transactions + unique customers)
Find total sales by gender
Find the most used payment method
Find the day with the highest total sales

Technologies: java.util.stream, BigDecimal, LocalDate, Collectors

2. Producer–Consumer Problem using wait/notify

Implements the classic producer–consumer problem with proper thread synchronization
Tasks implemented:
Source container (integers + doubles)
Destination container with same capacity
Bounded queue with half capacity
Producer thread writes to queue, waits when full
Consumer thread reads from queue, waits when empty
Tests verify data copied correctly in order

Technologies: Thread, synchronized, wait(), notifyAll()

Example Output

Both assignments were executed successfully.
The output screenshots are saved under Assignments output screenshots.pdf
