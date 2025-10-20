Assignment 2 — Producer–Consumer Problem (Java)
Overview

This project implements Assignment 2 of the Level 2 Coding Challenge using Java threads and synchronization.
It demonstrates how a producer and a consumer coordinate through a bounded queue to safely transfer data between containers.

Implemented Tasks

Create a source container with integers and doubles

Create a destination container with the same capacity

Create a queue with half the capacity of the source container

Implement a producer that writes from source to queue and waits when full

Implement a consumer that reads from queue to destination and waits when empty

Verify that all numbers are copied correctly from source to destination

How to Run

From project root (ASSIGNMENTSOLUTION_2):

Compile
javac -encoding UTF-8 -d bin src/pc/*.java src/App2.java src/TestProducerConsumer.java

Run manual verification (step-by-step checks)
java -cp bin App2

Run automated test (enable assertions)
java -ea -cp bin TestProducerConsumer

Example Output

Source container created with data: [1, 2.5, 3, 4.75, 5, 6.25, 7, 8.5]
Capacity: 8

Destination container created with capacity = 8

Queue created with capacity = 4

Producer and Consumer threads have finished execution.

Checking results...
Source: [1, 2.5, 3, 4.75, 5, 6.25, 7, 8.5]
Destination: [1, 2.5, 3, 4.75, 5, 6.25, 7, 8.5]
Equal: true
All checks passed — Producer/Consumer logic verified.

Notes

Uses wait() / notifyAll() for thread coordination

Queue capacity = half of source container (minimum 1)

Maintains original order of elements

TestProducerConsumer confirms correctness with random large data sets

Output shows progress for each assignment requirement
