# TrafficController

This project implements a programm managing the traffic over an one-lane Bridge. <br/>
The main aim was to get a feeling for Java Thread Concurrency and it was created for my class [Programmiersprachen und -konzepte](https://ufind.univie.ac.at/de/course.html?lv=051030&semester=2020W) at the University of Vienna.

## Structure

At it´s core, the program has 2 different implementations of the `TrafficController` Interface: <br/>
* `TrafficControllerSimple` manages Multithreading via the `synchronized` keyword
* `TrafficControllerFair` does so by using a `ReentrantLock` and Condition Variables

Apart from that, there´s a `TrafficRegistrar` logging the entering and leaving vehicles for testing purposes.


## What I´ve learned
* Synchronisation using different methods
* Avoiding Deadlocks 
* Being careful with Shared Variables
* Multithreading can be a ~~frustrating~~ tricky topic
