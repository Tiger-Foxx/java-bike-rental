package services;

import java.time.LocalDate;
import java.util.ArrayList;
import models.Bike;
import models.Client;
import models.Rental;

public class RentalService {
    private ArrayList<Rental> activeRentals = new ArrayList<>();

    public boolean rentBike(Client client, Bike bike) {
        if (client.getActiveRentals() >= 5) {
            System.out.println("Erreur: Le client a déjà 5 vélos loués.");
            return false;
        }

        if (bike.isRented()) {
            System.out.println("Erreur: Ce vélo est déjà loué.");
            return false;
        }

        Rental rental = new Rental(client, bike, LocalDate.now());
        activeRentals.add(rental);
        bike.setRented(true);
        client.incrementRentals();
        return true;
    }

    public boolean returnBike(Client client, Bike bike) {
        Rental rentalToRemove = null;
        for (Rental rental : activeRentals) {
            if (rental.getClient().equals(client) && rental.getBike().equals(bike)) {
                rentalToRemove = rental;
                break;
            }
        }

        if (rentalToRemove != null) {
            activeRentals.remove(rentalToRemove);
            bike.setRented(false);
            client.decrementRentals();
            return true;
        }
        return false;
    }

    public ArrayList<Rental> getActiveRentals() {
        return activeRentals;
    }

    public ArrayList<Rental> getClientRentals(Client client) {
        ArrayList<Rental> clientRentals = new ArrayList<>();
        for (Rental rental : activeRentals) {
            if (rental.getClient().equals(client)) {
                clientRentals.add(rental);
            }
        }
        return clientRentals;
    }
}