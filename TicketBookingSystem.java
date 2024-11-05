import java.util.Scanner;

abstract class Ticket {
    private int ticketNumber;
    private String passengerName;
    private String departure;
    private String destination;

    public Ticket(int ticketNumber, String passengerName, String departure, String destination) {
        this.ticketNumber = ticketNumber;
        this.passengerName = passengerName;
        this.departure = departure;
        this.destination = destination;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public abstract double calculatePrice();

    public void displayTicketDetails() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Departure: " + departure);
        System.out.println("Destination: " + destination);
        System.out.println("Price: " + calculatePrice() + " INR");
    }
}

class TrainTicket extends Ticket {
    private double distance;
    private static final double COST_PER_KM = 1.67;

    public TrainTicket(int ticketNumber, String passengerName, String departure, String destination, double distance) {
        super(ticketNumber, passengerName, departure, destination);
        this.distance = distance;
    }

    @Override
    public double calculatePrice() {
        return distance * COST_PER_KM;
    }
}

class FlightTicket extends Ticket {
    private String flightClass;
    private double distance;
    private static final double BASE_PRICE_ECONOMY = 200.0;
    private static final double BASE_PRICE_BUSINESS = 500.0;
    private static final double BASE_PRICE_FIRST = 1000.0;

    public FlightTicket(int ticketNumber, String passengerName, String departure, String destination, String flightClass, double distance) {
        super(ticketNumber, passengerName, departure, destination);
        this.flightClass = flightClass;
        this.distance = distance;
    }

    @Override
    public double calculatePrice() {
        double pricePerKm;
        double basePrice;

        switch (flightClass) {
            case "Economy":
                pricePerKm = 2.0;
                basePrice = BASE_PRICE_ECONOMY;
                break;
            case "Business":
                pricePerKm = 5.0;
                basePrice = BASE_PRICE_BUSINESS;
                break;
            case "First":
                pricePerKm = 10.0;
                basePrice = BASE_PRICE_FIRST;
                break;
            default:
                return 0.0;
        }
        return basePrice + (pricePerKm * distance);
    }
}

class TicketManager {
    private Ticket[] tickets = new Ticket[100];  
    private int ticketCount = 0;  

    public void addTicket(Ticket ticket) {
        if (ticketCount < tickets.length) {
            tickets[ticketCount] = ticket;
            ticketCount++;
        } else {
            System.out.println("Ticket storage is full!");
        }
    }

    public void displayAllTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets booked yet.");
            return;
        }
        for (int i = 0; i < ticketCount; i++) {
            tickets[i].displayTicketDetails();
            System.out.println("----------------------------");
        }
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketManager ticketManager = new TicketManager();

        System.out.println("Welcome to the Ticket Management System");
        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Book Train Ticket");
            System.out.println("2. Book Flight Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Ticket Number:");
                    int trainTicketNumber = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter Passenger Name:");
                    String trainPassengerName = scanner.nextLine();

                    System.out.println("Enter Departure Location:");
                    String trainDeparture = scanner.nextLine();

                    System.out.println("Enter Destination:");
                    String trainDestination = scanner.nextLine();

                    System.out.println("Enter Distance (in km):");
                    double trainDistance = scanner.nextDouble();

                    Ticket trainTicket = new TrainTicket(trainTicketNumber, trainPassengerName, trainDeparture, trainDestination, trainDistance);
                    ticketManager.addTicket(trainTicket);

                    System.out.println("Train ticket booked successfully!");
                    break;

                case 2:
                    System.out.println("Enter Ticket Number:");
                    int flightTicketNumber = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter Passenger Name:");
                    String flightPassengerName = scanner.nextLine();

                    System.out.println("Enter Departure Location:");
                    String flightDeparture = scanner.nextLine();

                    System.out.println("Enter Destination:");
                    String flightDestination = scanner.nextLine();

                    System.out.println("Enter Flight Class (Economy/Business/First):");
                    String flightClass = scanner.nextLine();

                    System.out.println("Enter Distance (in km):");
                    double flightDistance = scanner.nextDouble();

                    Ticket flightTicket = new FlightTicket(flightTicketNumber, flightPassengerName, flightDeparture, flightDestination, flightClass, flightDistance);
                    ticketManager.addTicket(flightTicket);

                    System.out.println("Flight ticket booked successfully!");
                    break;

                case 3:
                    ticketManager.displayAllTickets();
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
