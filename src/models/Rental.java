package models;

import java.time.LocalDate;


public class Rental {
    private Client client;
    private Bike bike;
    private LocalDate rentalDate;

    public Rental(Client client, Bike bike, LocalDate rentalDate) {
        this.client = client;
        this.bike = bike;
        this.rentalDate = rentalDate;
    }

    // Getters
    public Client getClient() {
        return client;
    }

    public Bike getBike() {
        return bike;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }
}
