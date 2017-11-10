# Ticket Service

A simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance.

## Instructions for building and executing the project

1. Clone the project from GitHub
   * **git clone https://github.com/iarihda/ticketservice.git** 
1. Change to the project directory
   * **cd ticketservice**
1. Build the project
   * **mvn package**
1. Run the unit test cases
   * **mvn test**
1. Run the application
   * **cd target**
   * **java -jar ticketsevice-0.0.1-SNAPSHOT.jar**
   
## Assumptions

* The best seats are the ones farthest from the screen. So, the seats are filled from the top row first column and descends accordingly (More on this in the implementation section of Design_Document.pdf). 
* The wait time for the hold is assumed to be 30 seconds. After which the hold will expire.
* The venue is considered to hold 5 rows and 5 columns for now. 
* The maximum number of rows that can be in the venue is 25. This has been set considering the naming convention. 
* The main method of the application and test cases are written based on these assumptions. 
* The values of no. of rows and columns and wait time can be changed in TheatreSetup.java, but then the tests and main method should be modified to work.
